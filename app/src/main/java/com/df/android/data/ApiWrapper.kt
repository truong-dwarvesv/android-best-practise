package com.df.android.data

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Handle only response for retrofit client
 */
suspend fun <T> safeCallApi(
    api: suspend() -> Response<T>
): NetworkResult<T> {
    return try {
        val response = api.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            throw HttpException(response)
        }
    } catch (throwable: Throwable) {
        val errorType = when (throwable) {
            is HttpException -> {
                val response = throwable.response()
                response?.errorBody()?.string()
                    ?.let {
                        Gson().fromJson(it, BaseErrorDto::class.java)
                    }
                    ?.let {
                        ErrorType.ResponseError(
                            it.code, it.message
                        )
                    } ?:
                ErrorType.NetworkError(
                    httpCode = response?.code(),  message = response?.message() ?: throwable.localizedMessage
                )
            }
            is IOException -> {
                ErrorType.NetworkError(
                    message = throwable.localizedMessage
                )
            }
            else -> {
                ErrorType.ResponseError(
                    message = throwable.localizedMessage
                )
            }
        }
        NetworkResult.Error(errorType)
    }
}

data class BaseErrorDto(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("message")
    val message: String
) : java.io.Serializable
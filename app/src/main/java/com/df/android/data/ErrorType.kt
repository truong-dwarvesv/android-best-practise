package com.df.android.data

sealed class ErrorType{
    class ResponseError(val errorCode: String? = null, val message: String = "Response Error") :
        ErrorType()

    class NetworkError(val httpCode: Int? = null, val message: String = "Network Error") :
        ErrorType()

    class ServerError(val message: String = "Server internal error") : ErrorType()

    // For other error
    class CustomError(val throwable: Throwable?) : ErrorType()
}

fun ErrorType.message(): String {
    return when (this) {
        is ErrorType.ResponseError -> this.message
        is ErrorType.NetworkError -> this.message
        is ErrorType.ServerError -> this.message
        is ErrorType.CustomError -> this.throwable?.localizedMessage ?: "Something wrongs. Oops!"
    }
}


/**
 * Handle error code not null. Return null if it handled
 */
fun ErrorType.handleErrorCode(block: (String) -> Unit): ErrorType? {
    when (this) {
        is ErrorType.ResponseError -> {
            if(errorCode != null){
                block.invoke(errorCode)
                return null
            }
        }
    }
    return this
}

/**
 * Handle exactly error code. Return null if it handled
 */
fun ErrorType.handleErrorCode(code: String, block: (String) -> Unit): ErrorType? {
    when (this) {
        is ErrorType.ResponseError -> {
            if(code == errorCode) {
                block.invoke(code)
                return null
            }
        }
    }
    return this
}
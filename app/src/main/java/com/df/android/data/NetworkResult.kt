package com.df.android.data

sealed interface NetworkResult<out T> {

    data class Success<out T>(val value: T) : NetworkResult<T>

    data class Error(val type: ErrorType) : NetworkResult<Nothing>

    fun isSuccess() = this is Success

    fun isError() = this is Error

    fun value(): T? {
        return when (this) {
            is Success -> this.value
            else -> null
        }
    }

    fun error(): ErrorType? {
        return when (this) {
            is Error -> this.type
            else -> null
        }
    }

}

inline fun <T, P : Any> NetworkResult<T>.map(block: (T) -> P): NetworkResult<P> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(block(this.value))
        is NetworkResult.Error -> this
    }
}

inline fun <T> NetworkResult<T>.doOnSuccess(block: (T) -> Unit): NetworkResult<T> {
    when (this) {
        is NetworkResult.Success -> {
            block.invoke(this.value)
        }
    }
    return this
}

inline fun <T> NetworkResult<T>.doOnError(block: (ErrorType) -> Unit): NetworkResult<T> {
    when (this) {
        is NetworkResult.Error -> {
            block.invoke(this.type)
        }
    }
    return this
}

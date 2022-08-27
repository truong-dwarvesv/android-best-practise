package com.df.android.data

sealed interface Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>

    data class Error(val errorType: ErrorType) : Resource<Nothing>

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
            is Error -> this.errorType
            else -> null
        }
    }

}

inline fun <T, P : Any> Resource<T>.map(block: (T) -> P): Resource<P> {
    return when (this) {
        is Resource.Success -> Resource.Success(block(this.value))
        is Resource.Error -> this
    }
}

inline fun <T> Resource<T>.doOnSuccess(block: (T) -> Unit): Resource<T> {
    when (this) {
        is Resource.Success -> {
            block.invoke(this.value)
        }
    }
    return this
}

inline fun <T> Resource<T>.doOnError(block: (ErrorType) -> Unit): Resource<T> {
    when (this) {
        is Resource.Error -> {
            block.invoke(this.errorType)
        }
    }
    return this
}

package com.df.android.data

sealed class UiState<T> {

    data class Success<T>(val data: T) : UiState<T>()

    class Error<T>(val error: ErrorType) : UiState<T>()

    class Loading<T> : UiState<T>()

    fun value(): T? {
        return when (this) {
            is Success -> this.data
            else -> null
        }
    }

    fun error(): ErrorType? {
        return when (this) {
            is Error -> this.error
            else -> null
        }
    }
}
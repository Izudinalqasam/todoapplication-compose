package com.example.todoapplication.presentation.state

sealed class UIState<out T> {
    object Initial : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    data class Error<T>(
        val message: String
    ) : UIState<T>()

    data class Success<T>(
        val data: T
    ) : UIState<T>()
}

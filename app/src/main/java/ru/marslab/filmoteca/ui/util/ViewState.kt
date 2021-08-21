package ru.marslab.filmoteca.ui.util

sealed class ViewState {
    data class Successful<out T>(val data: T) : ViewState()
    data class LoadError(val message: String) : ViewState()
    object Loading : ViewState()
}
package ru.marslab.filmoteca.ui.util

sealed class ViewState {
    data class Successful <out T> (val data: T) : ViewState()
    class Error (message: String): ViewState()
    object Loading: ViewState()
}
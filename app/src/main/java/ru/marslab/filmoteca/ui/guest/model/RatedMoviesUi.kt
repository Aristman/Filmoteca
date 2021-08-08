package ru.marslab.filmoteca.ui.guest.model

data class RatedMoviesUi(
    val id: Int,
    val title: String,
    val poster: String?,
    val releaseDate: String,
    val rating: String
)

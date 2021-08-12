package ru.marslab.filmoteca.ui.model

data class MovieShortUi(
    val id: Int,
    val title: String,
    val poster: String?,
    val releaseDate: String,
    val rating: Double
)

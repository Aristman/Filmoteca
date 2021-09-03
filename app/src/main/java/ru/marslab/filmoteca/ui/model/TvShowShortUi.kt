package ru.marslab.filmoteca.ui.model

data class TvShowShortUi(
    val id: Int,
    val title: String,
    val poster: String?,
    val releaseDate: String,
    val seasonCount: Int,
    val UserRating: Double
)


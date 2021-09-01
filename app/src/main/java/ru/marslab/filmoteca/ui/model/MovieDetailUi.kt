package ru.marslab.filmoteca.ui.model

data class MovieDetailUi(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val backDrop: String?,
    val poster: String?,
    val genres: List<String>,
    val timing: Int?,
    val release: String,
    val popularity: Double,
    val userRating: Double,
    val voteCount: Int,
    val description: String
)
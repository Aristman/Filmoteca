package ru.marslab.filmoteca.ui.model

data class MovieDetailUi(
    val id: Int,
    val title: String,
    val titleOrigin: String,
    val poster: String?,
    val genres: List<String>,
    val timing: Int?,
    val release: String,
    val description: String
)
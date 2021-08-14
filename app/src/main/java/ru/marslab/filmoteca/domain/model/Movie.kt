package ru.marslab.filmoteca.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val budget: Int?,
    val genres: List<Int>,
    val popularity: Double,
    val poster: String?,
    val productionCompanies: List<String>,
    val release: String,
    val spokenLanguages: List<String>,
    val originalLanguage: String,
    val rating: Double,
    val description: String = ""
)

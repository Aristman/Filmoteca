package ru.marslab.filmoteca.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val adult: Boolean,
    val backDrop: String?,
    val budget: Int?,
    val genres: List<Int>,
    val genreString: String?,
    val poster: String?,
    val productionCompanies: List<String>,
    val release: String,
    val spokenLanguages: List<String>,
    val originalLanguage: String,
    val popularity: Double,
    val userRating: Double,
    val voteCount: Int,
    val timing: Int?,
    val description: String = ""
)

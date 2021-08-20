package ru.marslab.filmoteca.domain.model

data class TvShow(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val languages: List<String>,
    val genres: List<Int>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String?,
    val lastAirDate: String?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originalLanguage: String,
    val popularity: Double,
    val poster: String?,
    val type: String?,
    val rating: Double,
    val voteCount: Int,
    val description: String
)

package ru.marslab.filmoteca.ui.model

data class TvShowDetailUi(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val adult: Boolean,
    val backDrop: String?,
    val languages: List<String>,
    val genres: List<Int>,
    val genreString: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String?,
    val lastAirDate: String?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originalLanguage: String,
    val poster: String?,
    val type: String?,
    val popularity: Double,
    val userRating: Double,
    val voteCount: Int,
    val description: String
)

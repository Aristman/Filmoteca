package ru.marslab.filmoteca.data.model.find


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FindByIdNW(
    @SerialName("movie_results")
    val movieResults: List<Movies>,
    @SerialName("person_results")
    val personResults: List<Persons>,
    @SerialName("tv_episode_results")
    val tvEpisodeResults: List<TvEpisodes>,
    @SerialName("tv_results")
    val tvResults: List<Tv>,
    @SerialName("tv_season_results")
    val tvSeasonResults: List<TvSeason>
) {
    @Serializable
    data class Movies(
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("backdrop_path")
        val backdropPath: String?,
        @SerialName("genre_ids")
        val genreIds: List<Int>,
        @SerialName("id")
        val id: Int,
        @SerialName("original_language")
        val originalLanguage: String,
        @SerialName("original_title")
        val originalTitle: String,
        @SerialName("overview")
        val overview: String,
        @SerialName("popularity")
        val popularity: Double,
        @SerialName("poster_path")
        val posterPath: String?,
        @SerialName("release_date")
        val releaseDate: String,
        @SerialName("title")
        val title: String,
        @SerialName("video")
        val video: Boolean,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int
    )

    // Уточнить состав класса в API
    @Serializable
    data class Persons(
        val id: Int
    )

    // Уточнить состав класса в API
    @Serializable
    data class TvEpisodes(
        val id: Int
    )

    // Уточнить состав класса в API
    @Serializable
    data class Tv(
        val id: Int
    )

    // Уточнить состав класса в API
    @Serializable
    data class TvSeason(
        val id: Int
    )

}
package ru.marslab.filmoteca.data.model.find


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FindByIdNW(
    @SerializedName("movie_results")
    val movieResults: List<Movies>,
    @SerializedName("person_results")
    val personResults: List<Persons>,
    @SerializedName("tv_episode_results")
    val tvEpisodeResults: List<TvEpisodes>,
    @SerializedName("tv_results")
    val tvResults: List<Tv>,
    @SerializedName("tv_season_results")
    val tvSeasonResults: List<TvSeason>
) {
    @Serializable
    data class Movies(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
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
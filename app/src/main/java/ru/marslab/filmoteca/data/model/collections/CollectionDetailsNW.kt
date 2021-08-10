package ru.marslab.filmoteca.data.model.collections


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDetailsNW(
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("parts")
    val parts: List<Part>,
    @SerialName("poster_path")
    val posterPath: String?
) {
    @Serializable
    data class Part(
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
        val posterPath: String,
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
}
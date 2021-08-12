package ru.marslab.filmoteca.data.model.lists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListsNW(
    @SerialName("created_by")
    val createdBy: String,
    @SerialName("description")
    val description: String,
    @SerialName("favorite_count")
    val favoriteCount: Int,
    @SerialName("id")
    val id: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("item_count")
    val itemCount: Int,
    @SerialName("items")
    val items: List<Item>,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterPath: String
) {
    @Serializable
    data class Item(
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("backdrop_path")
        val backdropPath: String?,
        @SerialName("genre_ids")
        val genreIds: List<Int>,
        @SerialName("id")
        val id: Int,
        @SerialName("media_type")
        val mediaType: String,
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
package ru.marslab.filmoteca.data.model.account


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListsNW(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class Result(
        @SerialName("description")
        val description: String,
        @SerialName("favorite_count")
        val favoriteCount: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("iso_639_1")
        val iso6391: String,
        @SerialName("item_count")
        val itemCount: Int,
        @SerialName("list_type")
        val listType: String,
        @SerialName("name")
        val name: String,
        @SerialName("poster_path")
        val posterPath: String?
    )
}
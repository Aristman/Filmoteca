package ru.marslab.filmoteca.data.model.account


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListsNW(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    @Serializable
    data class Result(
        @SerializedName("description")
        val description: String,
        @SerializedName("favorite_count")
        val favoriteCount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("item_count")
        val itemCount: Int,
        @SerializedName("list_type")
        val listType: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("poster_path")
        val posterPath: String?
    )
}
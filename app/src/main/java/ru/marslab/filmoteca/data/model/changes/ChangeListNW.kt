package ru.marslab.filmoteca.data.model.changes


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeListNW(
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
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("id")
        val id: Int
    )
}
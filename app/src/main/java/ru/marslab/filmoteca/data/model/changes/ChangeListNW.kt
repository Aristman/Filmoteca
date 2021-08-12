package ru.marslab.filmoteca.data.model.changes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeListNW(
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
        @SerialName("adult")
        val adult: Boolean,
        @SerialName("id")
        val id: Int
    )
}
package ru.marslab.filmoteca.data.model.genres


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresListNW(
    @SerialName("genres")
    val genres: List<Genre>
) {
    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    )
}
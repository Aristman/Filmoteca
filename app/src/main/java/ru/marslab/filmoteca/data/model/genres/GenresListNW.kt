package ru.marslab.filmoteca.data.model.genres


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GenresListNW(
    @SerializedName("genres")
    val genres: List<Genre>
) {
    @Serializable
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}
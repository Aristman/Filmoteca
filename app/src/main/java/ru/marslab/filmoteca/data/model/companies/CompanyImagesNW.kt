package ru.marslab.filmoteca.data.model.companies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyImagesNW(
    @SerialName("id")
    val id: Int,
    @SerialName("logos")
    val logos: List<Logo>
) {
    @Serializable
    data class Logo(
        @SerialName("aspect_ratio")
        val aspectRatio: Double,
        @SerialName("file_path")
        val filePath: String,
        @SerialName("file_type")
        val fileType: String,
        @SerialName("height")
        val height: Int,
        @SerialName("id")
        val id: String,
        @SerialName("vote_average")
        val voteAverage: Int,
        @SerialName("vote_count")
        val voteCount: Int,
        @SerialName("width")
        val width: Int
    )
}
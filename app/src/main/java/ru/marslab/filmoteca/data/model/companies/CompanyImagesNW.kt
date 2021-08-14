package ru.marslab.filmoteca.data.model.companies


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyImagesNW(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logos")
    val logos: List<Logo>
) {
    @Serializable
    data class Logo(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        @SerializedName("file_type")
        val fileType: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("id")
        val id: String,
        @SerializedName("vote_average")
        val voteAverage: Int,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("width")
        val width: Int
    )
}
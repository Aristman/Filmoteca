package ru.marslab.filmoteca.data.model.collections


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionImagesNW(
    @SerialName("backdrops")
    val backdrops: List<Backdrop>,
    @SerialName("id")
    val id: Int,
    @SerialName("posters")
    val posters: List<Poster>
) {
    @Serializable
    data class Backdrop(
        @SerialName("aspect_ratio")
        val aspectRatio: Double,
        @SerialName("file_path")
        val filePath: String,
        @SerialName("height")
        val height: Int,
        @SerialName("iso_639_1")
        val iso6391: String?,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int,
        @SerialName("width")
        val width: Int
    )

    @Serializable
    data class Poster(
        @SerialName("aspect_ratio")
        val aspectRatio: Double,
        @SerialName("file_path")
        val filePath: String,
        @SerialName("height")
        val height: Int,
        @SerialName("iso_639_1")
        val iso6391: String,
        @SerialName("vote_average")
        val voteAverage: Double,
        @SerialName("vote_count")
        val voteCount: Int,
        @SerialName("width")
        val width: Int
    )
}
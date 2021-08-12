package ru.marslab.filmoteca.data.model.collections


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionTranslationsNW(
    @SerialName("id")
    val id: Int,
    @SerialName("translations")
    val translations: List<Translation>
) {
    @Serializable
    data class Translation(
        @SerialName("data")
        val `data`: Data,
        @SerialName("english_name")
        val englishName: String,
        @SerialName("iso_3166_1")
        val iso31661: String,
        @SerialName("iso_639_1")
        val iso6391: String,
        @SerialName("name")
        val name: String
    ) {
        @Serializable
        data class Data(
            @SerialName("homepage")
            val homepage: String,
            @SerialName("overview")
            val overview: String,
            @SerialName("title")
            val title: String
        )
    }
}
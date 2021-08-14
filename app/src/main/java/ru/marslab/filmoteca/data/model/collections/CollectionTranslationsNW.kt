package ru.marslab.filmoteca.data.model.collections


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionTranslationsNW(
    @SerializedName("id")
    val id: Int,
    @SerializedName("translations")
    val translations: List<Translation>
) {
    @Serializable
    data class Translation(
        @SerializedName("data")
        val `data`: Data,
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    ) {
        @Serializable
        data class Data(
            @SerializedName("homepage")
            val homepage: String,
            @SerializedName("overview")
            val overview: String,
            @SerializedName("title")
            val title: String
        )
    }
}
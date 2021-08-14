package ru.marslab.filmoteca.data.model.credits


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreditNW(
    @SerializedName("credit_type")
    val creditType: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("media")
    val media: Media,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("person")
    val person: Person
) {
    @Serializable
    data class Media(
        @SerializedName("character")
        val character: String,
        @SerializedName("episodes")
        val episodes: List<String>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("seasons")
        val seasons: List<Season>
    ) {
        @Serializable
        data class Season(
            @SerializedName("air_date")
            val airDate: String,
            @SerializedName("poster_path")
            val posterPath: String,
            @SerializedName("season_number")
            val seasonNumber: Int
        )
    }

    @Serializable
    data class Person(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}
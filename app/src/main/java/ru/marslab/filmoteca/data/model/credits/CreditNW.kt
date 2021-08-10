package ru.marslab.filmoteca.data.model.credits


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditNW(
    @SerialName("credit_type")
    val creditType: String,
    @SerialName("department")
    val department: String,
    @SerialName("id")
    val id: String,
    @SerialName("job")
    val job: String,
    @SerialName("media")
    val media: Media,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("person")
    val person: Person
) {
    @Serializable
    data class Media(
        @SerialName("character")
        val character: String,
        @SerialName("episodes")
        val episodes: List<String>,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("original_name")
        val originalName: String,
        @SerialName("seasons")
        val seasons: List<Season>
    ) {
        @Serializable
        data class Season(
            @SerialName("air_date")
            val airDate: String,
            @SerialName("poster_path")
            val posterPath: String,
            @SerialName("season_number")
            val seasonNumber: Int
        )
    }

    @Serializable
    data class Person(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    )
}
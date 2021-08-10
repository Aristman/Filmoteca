package ru.marslab.filmoteca.data.model.configuration


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigTimeZonesNW(
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("zones")
    val zones: List<String>
)
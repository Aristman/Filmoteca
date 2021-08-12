package ru.marslab.filmoteca.data.model.configuration


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigCountriesNW(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_3166_1")
    val iso31661: String
)

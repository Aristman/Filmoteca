package ru.marslab.filmoteca.data.model.configuration


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigTimeZonesNW(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("zones")
    val zones: List<String>
)
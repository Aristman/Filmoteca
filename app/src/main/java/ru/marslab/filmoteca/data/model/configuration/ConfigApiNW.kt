package ru.marslab.filmoteca.data.model.configuration


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigApiNW(
    @SerializedName("change_keys")
    val changeKeys: List<String>,
    @SerializedName("images")
    val images: Images
) {
    @Serializable
    data class Images(
        @SerializedName("backdrop_sizes")
        val backdropSizes: List<String>,
        @SerializedName("base_url")
        val baseUrl: String,
        @SerializedName("logo_sizes")
        val logoSizes: List<String>,
        @SerializedName("poster_sizes")
        val posterSizes: List<String>,
        @SerializedName("profile_sizes")
        val profileSizes: List<String>,
        @SerializedName("secure_base_url")
        val secureBaseUrl: String,
        @SerializedName("still_sizes")
        val stillSizes: List<String>
    )
}
package ru.marslab.filmoteca.data.model.certifications


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCertificationsNW(
    @SerializedName("certifications")
    val certifications: Certifications
) {
    @Serializable
    data class Certifications(
        @SerializedName("AU")
        val AU: List<Certificate>,
        @SerializedName("BG")
        val BG: List<Certificate>,
        @SerializedName("BR")
        val BR: List<Certificate>,
        @SerializedName("CA")
        val CA: List<Certificate>,
        @SerializedName("DE")
        val DE: List<Certificate>,
        @SerializedName("ES")
        val ES: List<Certificate>,
        @SerializedName("FI")
        val FI: List<Certificate>,
        @SerializedName("FR")
        val FR: List<Certificate>,
        @SerializedName("GB")
        val GB: List<Certificate>,
        @SerializedName("IN")
        val IN: List<Certificate>,
        @SerializedName("NL")
        val NL: List<Certificate>,
        @SerializedName("NZ")
        val NZ: List<Certificate>,
        @SerializedName("PH")
        val PH: List<Certificate>,
        @SerializedName("PT")
        val PT: List<Certificate>,
        @SerializedName("US")
        val US: List<Certificate>
    )
}
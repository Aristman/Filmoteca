package ru.marslab.filmoteca.data.model.certifications


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TvCertificationsNW(
    @SerializedName("certifications")
    val certifications: Certifications
) {
    @Serializable
    data class Certifications(
        @SerializedName("AU")
        val AU: List<Certificate>,
        @SerializedName("BR")
        val BR: List<Certificate>,
        @SerializedName("CA")
        val CA: List<Certificate>,
        @SerializedName("DE")
        val DE: List<Certificate>,
        @SerializedName("FR")
        val FR: List<Certificate>,
        @SerializedName("GB")
        val GB: List<Certificate>,
        @SerializedName("KR")
        val KR: List<Certificate>,
        @SerializedName("RU")
        val RU: List<Certificate>,
        @SerializedName("TH")
        val TH: List<Certificate>,
        @SerializedName("US")
        val US: List<Certificate>
    )
}
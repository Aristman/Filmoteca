package ru.marslab.filmoteca.data.model.certifications


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvCertificationsNW(
    @SerialName("certifications")
    val certifications: Certifications
) {
    @Serializable
    data class Certifications(
        @SerialName("AU")
        val AU: List<Certificate>,
        @SerialName("BR")
        val BR: List<Certificate>,
        @SerialName("CA")
        val CA: List<Certificate>,
        @SerialName("DE")
        val DE: List<Certificate>,
        @SerialName("FR")
        val FR: List<Certificate>,
        @SerialName("GB")
        val GB: List<Certificate>,
        @SerialName("KR")
        val KR: List<Certificate>,
        @SerialName("RU")
        val RU: List<Certificate>,
        @SerialName("TH")
        val TH: List<Certificate>,
        @SerialName("US")
        val US: List<Certificate>
    )
}
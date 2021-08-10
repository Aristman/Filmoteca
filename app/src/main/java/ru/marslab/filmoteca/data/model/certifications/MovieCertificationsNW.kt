package ru.marslab.filmoteca.data.model.certifications


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCertificationsNW(
    @SerialName("certifications")
    val certifications: Certifications
) {
    @Serializable
    data class Certifications(
        @SerialName("AU")
        val AU: List<Certificate>,
        @SerialName("BG")
        val BG: List<Certificate>,
        @SerialName("BR")
        val BR: List<Certificate>,
        @SerialName("CA")
        val CA: List<Certificate>,
        @SerialName("DE")
        val DE: List<Certificate>,
        @SerialName("ES")
        val ES: List<Certificate>,
        @SerialName("FI")
        val FI: List<Certificate>,
        @SerialName("FR")
        val FR: List<Certificate>,
        @SerialName("GB")
        val GB: List<Certificate>,
        @SerialName("IN")
        val IN: List<Certificate>,
        @SerialName("NL")
        val NL: List<Certificate>,
        @SerialName("NZ")
        val NZ: List<Certificate>,
        @SerialName("PH")
        val PH: List<Certificate>,
        @SerialName("PT")
        val PT: List<Certificate>,
        @SerialName("US")
        val US: List<Certificate>
    )
}
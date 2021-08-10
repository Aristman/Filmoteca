package ru.marslab.filmoteca.data.model.companies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyAlternativeNamesNW(
    @SerialName("id")
    val id: Int,
    @SerialName("results")
    val results: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("name")
        val name: String,
        @SerialName("type")
        val type: String
    )
}
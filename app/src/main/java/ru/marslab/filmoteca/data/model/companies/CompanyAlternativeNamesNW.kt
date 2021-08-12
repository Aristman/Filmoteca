package ru.marslab.filmoteca.data.model.companies


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyAlternativeNamesNW(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
) {
    @Serializable
    data class Result(
        @SerializedName("name")
        val name: String,
        @SerializedName("type")
        val type: String
    )
}
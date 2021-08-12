package ru.marslab.filmoteca.data.model.companies


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyDetailsNW(
    @SerializedName("description")
    val description: String,
    @SerializedName("headquarters")
    val headquarters: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
    @SerializedName("parent_company")
    val parentCompany: String?
)
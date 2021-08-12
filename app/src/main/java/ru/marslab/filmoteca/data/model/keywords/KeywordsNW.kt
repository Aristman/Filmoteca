package ru.marslab.filmoteca.data.model.keywords


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsNW(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
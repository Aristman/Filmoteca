package ru.marslab.filmoteca.data.model.keywords


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsNW(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
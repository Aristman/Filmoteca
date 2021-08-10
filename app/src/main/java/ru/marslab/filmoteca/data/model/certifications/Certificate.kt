package ru.marslab.filmoteca.data.model.certifications

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Certificate(
    @SerialName("certification")
    val certification: String,
    @SerialName("meaning")
    val meaning: String,
    @SerialName("order")
    val order: Int
)

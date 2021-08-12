package ru.marslab.filmoteca.data.model.certifications

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Certificate(
    @SerializedName("certification")
    val certification: String,
    @SerializedName("meaning")
    val meaning: String,
    @SerializedName("order")
    val order: Int
)

package ru.marslab.filmoteca.data.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewTokenNW(
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("request_token")
    val requestToken: String,
    @SerialName("success")
    val success: Boolean
)
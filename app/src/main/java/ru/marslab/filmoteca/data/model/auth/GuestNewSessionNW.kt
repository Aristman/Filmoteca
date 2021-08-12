package ru.marslab.filmoteca.data.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuestNewSessionNW(
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("guest_session_id")
    val guestSessionId: String,
    @SerialName("success")
    val success: Boolean
)
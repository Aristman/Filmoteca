package ru.marslab.filmoteca.data.model.auth


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GuestNewSessionNW(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("guest_session_id")
    val guestSessionId: String,
    @SerializedName("success")
    val success: Boolean
)
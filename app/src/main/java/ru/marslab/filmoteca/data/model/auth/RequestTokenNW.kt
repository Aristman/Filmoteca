package ru.marslab.filmoteca.data.model.auth


import com.google.gson.annotations.SerializedName


data class RequestTokenNW(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)
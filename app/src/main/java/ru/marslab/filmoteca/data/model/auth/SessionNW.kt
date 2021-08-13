package ru.marslab.filmoteca.data.model.auth


import com.google.gson.annotations.SerializedName

data class SessionNW(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)
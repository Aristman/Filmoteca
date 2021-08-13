package ru.marslab.filmoteca.domain.model

data class RequestToken(
    val token: String,
    val expDate: String
)

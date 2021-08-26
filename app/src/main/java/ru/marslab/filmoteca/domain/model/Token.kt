package ru.marslab.filmoteca.domain.model

data class Token(
    val id: String,
    val expiresAt: Long
)

package ru.marslab.filmoteca.domain.model

data class Actor(
    val id: Int,
    val name: String,
    val originalName: String,
    val photoPath: String?,
    val popularity: Double
)

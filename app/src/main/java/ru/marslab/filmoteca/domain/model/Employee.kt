package ru.marslab.filmoteca.domain.model

data class Employee(
    val id: Int,
    val name: String,
    val originalName: String,
    val photoPath: String?,
    val department: String,
    val job: String
)

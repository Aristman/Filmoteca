package ru.marslab.filmoteca.data.model.configuration


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigJobsNW(
    @SerialName("department")
    val department: String,
    @SerialName("jobs")
    val jobs: List<String>
)
package ru.marslab.filmoteca.data.model.configuration


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigJobsNW(
    @SerializedName("department")
    val department: String,
    @SerializedName("jobs")
    val jobs: List<String>
)
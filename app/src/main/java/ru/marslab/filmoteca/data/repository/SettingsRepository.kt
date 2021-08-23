package ru.marslab.filmoteca.data.repository

import ru.marslab.filmoteca.data.model.configuration.*

interface SettingsRepository {
    suspend fun getApiConfig(): ConfigApiNW?
    suspend fun getCountriesConfig(): List<ConfigCountriesNW>?
    suspend fun getJobsConfig(): List<ConfigJobsNW>?
    suspend fun getLanguagesConfig(): List<ConfigLanguagesNW>?
    suspend fun getTimeZonesConfig(): List<ConfigTimeZonesNW>?
}
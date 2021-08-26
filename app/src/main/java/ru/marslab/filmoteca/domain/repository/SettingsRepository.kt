package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.*

interface SettingsRepository {
    suspend fun getApiConfig(): ConfigApi?
    suspend fun getCountriesConfig(): List<Country>?
    suspend fun getJobsConfig(): List<Job>?
    suspend fun getLanguagesConfig(): List<Language>?
    suspend fun getTimeZonesConfig(): List<TimeZone>?
}
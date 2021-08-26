package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.*
import ru.marslab.filmoteca.domain.repository.SettingsRepository
import ru.marslab.filmoteca.domain.repository.Storage

class SettingsRepositoryImpl(
    private val api: MovieApi,
    private val storage: Storage
): SettingsRepository {

    override suspend fun getApiConfig(): ConfigApi? {
        val configApi = api.getConfigApi(storage.getApikeyV3())
        return checkResponse(configApi)?.body()?.toDomain()
    }

    override suspend fun getCountriesConfig(): List<Country>? {
        val configCountries = api.getConfigCountries(storage.getApikeyV3())
        return checkResponse(configCountries)?.body()?.map { it.toDomain() }
    }

    override suspend fun getJobsConfig(): List<Job>? {
        val jobsConfig = api.getJobsConfig(storage.getApikeyV3())
        return checkResponse(jobsConfig)?.body()?.map { it.toDomain() }
    }

    override suspend fun getLanguagesConfig(): List<Language>? {
        val configLanguages = api.getConfigLanguages(storage.getApikeyV3())
        return checkResponse(configLanguages)?.body()?.map { it.toDomain() }
    }

    override suspend fun getTimeZonesConfig(): List<TimeZone>? {
        val configTimeZones = api.getConfigTimeZones(storage.getApikeyV3())
        return checkResponse(configTimeZones)?.body()?.map { it.toDomain() }
    }
}
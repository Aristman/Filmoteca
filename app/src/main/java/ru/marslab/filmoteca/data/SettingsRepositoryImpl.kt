package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.*
import ru.marslab.filmoteca.domain.repository.SettingsRepository
import ru.marslab.filmoteca.domain.repository.Store

class SettingsRepositoryImpl(
    private val api: MovieApi,
    private val store: Store
): SettingsRepository {

    override suspend fun getApiConfig(): ConfigApi? {
        val configApi = api.getConfigApi(store.apiKeyV3)
        return checkResponse(configApi)?.body()?.toDomain()
    }

    override suspend fun getCountriesConfig(): List<Country>? {
        val configCountries = api.getConfigCountries(store.apiKeyV3)
        return checkResponse(configCountries)?.body()?.map { it.toDomain() }
    }

    override suspend fun getJobsConfig(): List<Job>? {
        val jobsConfig = api.getJobsConfig(store.apiKeyV3)
        return checkResponse(jobsConfig)?.body()?.map { it.toDomain() }
    }

    override suspend fun getLanguagesConfig(): List<Language>? {
        val configLanguages = api.getConfigLanguages(store.apiKeyV3)
        return checkResponse(configLanguages)?.body()?.map { it.toDomain() }
    }

    override suspend fun getTimeZonesConfig(): List<TimeZone>? {
        val configTimeZones = api.getConfigTimeZones(store.apiKeyV3)
        return checkResponse(configTimeZones)?.body()?.map { it.toDomain() }
    }
}
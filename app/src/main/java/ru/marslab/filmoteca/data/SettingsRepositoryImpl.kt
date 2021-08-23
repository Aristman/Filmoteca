package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.model.configuration.*
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.SettingsRepository
import ru.marslab.filmoteca.domain.repository.Store

class SettingsRepositoryImpl(
    private val api: MovieApi,
    private val store: Store
): SettingsRepository {

    override suspend fun getApiConfig(): ConfigApiNW? {
        val configApi = api.getConfigApi(store.apiKeyV3)
        return checkResponse(configApi)?.body()
    }

    override suspend fun getCountriesConfig(): List<ConfigCountriesNW>? {
        val configCountries = api.getConfigCountries(store.apiKeyV3)
        return checkResponse(configCountries)?.body()
    }

    override suspend fun getJobsConfig(): List<ConfigJobsNW>? {
        val jobsConfig = api.getJobsConfig(store.apiKeyV3)
        return checkResponse(jobsConfig)?.body()
    }

    override suspend fun getLanguagesConfig(): List<ConfigLanguagesNW>? {
        val configLanguages = api.getConfigLanguages(store.apiKeyV3)
        return checkResponse(configLanguages)?.body()
    }

    override suspend fun getTimeZonesConfig(): List<ConfigTimeZonesNW>? {
        val configTimeZones = api.getConfigTimeZones(store.apiKeyV3)
        return checkResponse(configTimeZones)?.body()
    }
}
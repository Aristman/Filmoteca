package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Country
import ru.marslab.filmoteca.domain.model.Job
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone

interface DatabaseRepository {
    suspend fun getMovieComment(id: Int): String?
    suspend fun saveMovieHistory(id: Int, lookTime: Long, comment: String)
    suspend fun saveCountries(countries: List<Country>)
    suspend fun saveJobs(jobs: List<Job>)
    suspend fun saveLanguages(languages: List<Language>)
    suspend fun saveTimeZones(timeZones: List<TimeZone>)
}
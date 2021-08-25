package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDb
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.data.room.entity.MoviesHistoryTable
import ru.marslab.filmoteca.domain.model.Country
import ru.marslab.filmoteca.domain.model.Job
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone
import ru.marslab.filmoteca.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(private val database: MainDatabase) : DatabaseRepository {
    override suspend fun getMovieComment(id: Int): String? {
        val movieData = database.moviesHistoryDao().getMovieData(id)
        return if (movieData.isNotEmpty()) {
            movieData.first().comment
        } else {
            null
        }
    }

    override suspend fun saveMovieHistory(id: Int, lookTime: Long, comment: String) {
        database.moviesHistoryDao().insertNewMovieData(
            MoviesHistoryTable(
                id,
                lookTime,
                comment
            )
        )
    }

    override suspend fun saveCountries(countries: List<Country>) {
        database.moviesHistoryDao().insertCountries(countries.map { it.toDb() })
    }

    override suspend fun saveJobs(jobs: List<Job>) {
        database.moviesHistoryDao().insertJobs(jobs.map { it.toDb() })
    }

    override suspend fun saveLanguages(languages: List<Language>) {
        database.moviesHistoryDao().insertLanguages(languages.map { it.toDb() })
    }

    override suspend fun getLanguages(): List<Language> =
        database.moviesHistoryDao().getLanguages().map { it.toDomain() }

    override suspend fun saveTimeZones(timeZones: List<TimeZone>) {
        database.moviesHistoryDao().insertTimeZones(timeZones.map { it.toDb() })
    }

}
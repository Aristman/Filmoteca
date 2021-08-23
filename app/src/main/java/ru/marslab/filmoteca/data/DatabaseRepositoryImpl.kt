package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.room.database.MainDatabase
import ru.marslab.filmoteca.data.room.entity.MoviesHistoryTable
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
        database.moviesHistoryDao().insertNewMovieData(MoviesHistoryTable(
            id,
            lookTime,
            comment
        ))
    }

}
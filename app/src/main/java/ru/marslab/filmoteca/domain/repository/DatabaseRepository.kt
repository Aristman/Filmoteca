package ru.marslab.filmoteca.domain.repository

interface DatabaseRepository {
    suspend fun getMovieComment(id: Int): String?
    suspend fun saveMovieHistory(id: Int, lookTime: Long, comment: String)
}
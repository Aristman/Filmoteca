package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.domain.model.Movie

interface Repository {
    val apiKeyV3: String
    val apiKeyV4: String
    var sessionId: String?

    suspend fun getSessionId(): String?
    suspend fun getRatedMovies(): List<Movie>
    suspend fun getMovieDetail(id: Int): Movie?

}
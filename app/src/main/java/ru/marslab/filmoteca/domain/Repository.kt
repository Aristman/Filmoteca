package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.domain.model.Movie

interface Repository {
    val apiKeyV3: String
    val apiKeyV4: String
    var sessionId: String?

    suspend fun takeSessionId(): String?
    suspend fun getGuestRatedMovies(): List<Movie>
    suspend fun getMovieDetail(id: Int): Movie?

}
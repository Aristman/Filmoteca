package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.RequestToken

interface Repository {
    companion object {
        var sessionId: String? = null
        var requestToken: RequestToken? = null
    }
    val apiKeyV3: String
    val apiKeyV4: String

    suspend fun createRequestToken(): RequestToken?
    suspend fun createGuestSession(): String?
    suspend fun getGuestRatedMovies(): List<Movie>
    suspend fun getMovieDetail(id: Int): Movie?

}
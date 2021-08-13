package ru.marslab.filmoteca.data

import android.util.Log
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.Repository
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.util.Constants.LOG_TAG

class RepositoryNetworkImpl(
    private val api: MovieApi,
) : Repository, GuestRepository, MovieRepository {

    override suspend fun createRequestToken(): RequestToken? {
        val createdRequestToken = api.createRequestToken(Store.apiKeyV3)
        if (createdRequestToken.isSuccessful) {
            Log.d(LOG_TAG, createdRequestToken.body().toString())
            val toDomain = createdRequestToken.body()?.toDomain()
            Store.requestToken = toDomain
        }
        return Store.requestToken
    }

    override suspend fun createGuestSession(): String? {
        Store.guestSessionId = try {
            val guestSessionId = api.createGuestSessionId(Store.apiKeyV3)
            if (guestSessionId.isSuccessful) {
                Log.d(LOG_TAG, guestSessionId.body().toString())
                guestSessionId.body()?.guestSessionId
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return Store.guestSessionId
    }

    override suspend fun getGuestRatedMovies(): List<Movie> {
        Store.guestSessionId?.let {
            val ratedMovies = api.getGuestRatedMovies(it, Store.apiKeyV3)
            ratedMovies.body()?.let { guestRatedMoviesNW ->
                Log.d(LOG_TAG, ratedMovies.body().toString())
                return guestRatedMoviesNW.toDomain()
            }
        }
        // Доработать обработку ошибки получения списка фильмов
        return emptyList()
    }

    override suspend fun getMovieDetail(id: Int): Movie? {
        return null
    }
}
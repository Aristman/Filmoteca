package ru.marslab.filmoteca.data

import android.util.Log
import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.util.Constants.LOG_TAG

class RepositoryNetworkImpl (
    private val api: MovieApi,
) : Repository {
    override val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    override val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4
    override var sessionId: String? = null

    override suspend fun takeSessionId(): String? {
        sessionId = try {
            val guestSessionId = api.getGuestSessionId(apiKeyV3)
            if (guestSessionId.isSuccessful) {
                Log.d(LOG_TAG, guestSessionId.body().toString())
                guestSessionId.body()?.guestSessionId
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return sessionId
    }

    override suspend fun getGuestRatedMovies(): List<Movie> {
        sessionId?.let{
            val ratedMovies = api.getGuestRatedMovies(it, apiKeyV3)
            ratedMovies.body()?.let { guestRatedMoviesNW ->
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
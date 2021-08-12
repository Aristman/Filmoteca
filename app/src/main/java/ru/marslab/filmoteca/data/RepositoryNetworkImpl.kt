package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.model.Movie

class RepositoryNetworkImpl (
    private val api: MovieApi,
) : Repository {
    override val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    override val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4
    override var sessionId: String? = null

    override suspend fun getSessionId(): String? {
        sessionId = try {
            val guestSessionId = api.getGuestSessionId(apiKeyV3)
            if (guestSessionId.isSuccessful) {
                guestSessionId.body()?.guestSessionId
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return sessionId
    }

    override suspend fun getRatedMovies(): List<Movie> {
        sessionId?.let{
            val ratedMovies = api.getRatedMovies(it, apiKeyV3)
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
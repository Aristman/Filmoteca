package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.ui.util.logMessage


class GuestRepositoryImpl(
    private val api: MovieApi
): GuestRepository {

    override suspend fun createGuestSession(): String? {
        Store.sessionId = try {
            val guestSessionId = api.createGuestSessionId(Store.apiKeyV3)
            if (guestSessionId.isSuccessful) {
                logMessage(guestSessionId.body().toString())
                guestSessionId.body()?.guestSessionId
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return Store.sessionId
    }

    override suspend fun getGuestRatedMovies(): List<Movie> {
        Store.sessionId?.let {
            val ratedMovies = api.getGuestRatedMovies(it, Store.apiKeyV3)
            ratedMovies.body()?.let { guestRatedMoviesNW ->
                logMessage(ratedMovies.body().toString())
                return guestRatedMoviesNW.toDomain()
            }
        }
        // Доработать обработку ошибки получения списка фильмов
        return emptyList()
    }
}
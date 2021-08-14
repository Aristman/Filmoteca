package ru.marslab.filmoteca.data

import android.util.Log
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.util.Constants


class GuestRepositoryImpl(
    private val api: MovieApi
): GuestRepository {

    override suspend fun createGuestSession(): String? {
        Store.guestSessionId = try {
            val guestSessionId = api.createGuestSessionId(Store.apiKeyV3)
            if (guestSessionId.isSuccessful) {
                Log.d(Constants.LOG_TAG, guestSessionId.body().toString())
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
                Log.d(Constants.LOG_TAG, ratedMovies.body().toString())
                return guestRatedMoviesNW.toDomain()
            }
        }
        // Доработать обработку ошибки получения списка фильмов
        return emptyList()
    }
}
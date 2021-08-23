package ru.marslab.filmoteca.data

import retrofit2.Response
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.Store

class MovieRepositoryImpl(private val api: MovieApi, private val store: Store) : MovieRepository {

    override suspend fun getMovieDetails(id: Int): Movie? {
        val response = api.getMovieDetails(id, store.apiKeyV3)
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getPopularMovies(): List<Movie>? {
        val response = api.getPopularMovies(store.apiKeyV3)
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getTopRatedMovies(): List<Movie>? {
        val response = api.getTopRatedMovies(store.apiKeyV3)
        return checkResponse(response)?.body()?.toDomain()
    }
}

internal fun <T> checkResponse(response: Response<T>) =
    if (response.isSuccessful) {
        response
    } else {
        null
    }

package ru.marslab.filmoteca.data

import retrofit2.Response
import ru.marslab.filmoteca.data.model.movies.MovieDetailsNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.Store

class MovieRepositoryImpl(private val api: MovieApi, private val store: Store) : MovieRepository {

    override suspend fun getMovieDetails(id: Int): MovieDetailsNW? {
        val response = api.getMovieDetails(id, store.apiKeyV3)
        return checkResponse(response)?.body()
    }

    override suspend fun getPopularMovies(): MoviesNW? {
        val response = api.getPopularMovies(store.apiKeyV3)
        return checkResponse(response)?.body()
    }

    override suspend fun getTopRatedMovies(): MoviesNW? {
        val response = api.getTopRatedMovies(store.apiKeyV3)
        return checkResponse(response)?.body()
    }
}

internal fun <T> checkResponse(response: Response<T>) =
    if (response.isSuccessful) {
        response
    } else {
        null
    }

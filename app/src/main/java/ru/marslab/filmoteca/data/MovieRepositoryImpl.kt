package ru.marslab.filmoteca.data

import retrofit2.Response
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TimeZone
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.Storage

class MovieRepositoryImpl(private val api: MovieApi, private val storage: Storage) :
    MovieRepository {

    override suspend fun getMovieDetails(id: Int, language: Language?): Movie? {
        val response = api.getMovieDetails(id, storage.getApikeyV3(), language?.iso6391)
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getPopularMovies(
        language: Language?,
        page: Int?,
        region: TimeZone?
    ): List<Movie>? {
        val response = api.getPopularMovies(
            apiKey = storage.getApikeyV3(),
            language = language?.iso6391,
            page = page,
            region = region?.iso31661
        )
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getTopRatedMovies(
        language: Language?,
        page: Int?,
        region: TimeZone?
    ): List<Movie>? {
        val response =
            api.getTopRatedMovies(storage.getApikeyV3(), language?.iso6391, page, region?.iso31661)
        return checkResponse(response)?.body()?.toDomain()
    }
}

internal fun <T> checkResponse(response: Response<T>) =
    if (response.isSuccessful && response.body() != null) {
        response
    } else {
        null
    }

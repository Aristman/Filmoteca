package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.mapper.toMovie
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.TvRepository

class TvRepositoryImpl(private val api: MovieApi, private val storage: Storage) : TvRepository {

    override suspend fun getPopularTvShows(): List<TvShow>? {
        val response = api.getPopularTvShows(storage.getApikeyV3())
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getTvDetailInfo(id: Int): Movie? {
        val response = api.getTvDetails(id, storage.getApikeyV3())
        return checkResponse(response)?.body()?.toDomain()?.toMovie()
    }
}

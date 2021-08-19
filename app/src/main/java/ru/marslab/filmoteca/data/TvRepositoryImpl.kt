package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.repository.TvRepository

class TvRepositoryImpl(private val api: MovieApi) : TvRepository {

    override suspend fun getPopularTvShows(): TvShowsNW? {
        val response = api.getPopularTvShows(Store.apiKeyV3)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
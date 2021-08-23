package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.model.tv.TvShowDetailsNW
import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.repository.Store
import ru.marslab.filmoteca.domain.repository.TvRepository

class TvRepositoryImpl(private val api: MovieApi, private val store: Store) : TvRepository {

    override suspend fun getPopularTvShows(): TvShowsNW? {
        val response = api.getPopularTvShows(store.apiKeyV3)
        return checkResponse(response)?.body()
    }

    override suspend fun getTvDetailInfo(id: Int): TvShowDetailsNW? {
        val response = api.getTvDetails(id, store.apiKeyV3)
        return checkResponse(response)?.body()
    }
}

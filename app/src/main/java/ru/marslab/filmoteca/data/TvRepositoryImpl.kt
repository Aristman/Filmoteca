package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.TvRepository

class TvRepositoryImpl(private val api: MovieApi, private val storage: Storage) : TvRepository {

    override suspend fun getPopularTvShows(language: Language?, page: Int?): List<TvShow>? {
        val response = api.getPopularTvShows(storage.getApikeyV3(), language?.iso6391, page)
        return checkResponse(response)?.body()?.toDomain()
    }

    override suspend fun getTvDetailInfo(id: Int, language: Language?): TvShow? {
        val response = api.getTvDetails(id, storage.getApikeyV3(), language?.iso6391)
        return checkResponse(response)?.body()?.toDomain()
    }
}

package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TvShow

interface TvRepository {

    suspend fun getPopularTvShows(
        language: Language? = null,
        page: Int? = null
    ): List<TvShow>?

    suspend fun getTvDetailInfo(
        id: Int,
        language: Language? = null
    ): Movie?
}
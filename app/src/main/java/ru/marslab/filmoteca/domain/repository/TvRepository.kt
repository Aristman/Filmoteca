package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TvShow

interface TvRepository {

    suspend fun getPopularTvShows(): List<TvShow>?
    suspend fun getTvDetailInfo(id: Int): Movie?
}
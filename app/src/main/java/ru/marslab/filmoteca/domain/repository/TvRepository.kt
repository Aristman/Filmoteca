package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.data.model.tv.TvShowsNW

interface TvRepository {

    suspend fun getPopularTvShows(): TvShowsNW?
}
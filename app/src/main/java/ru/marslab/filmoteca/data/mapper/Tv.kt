package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.tv.TvShowDetailsNW
import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.domain.repository.Constants


fun TvShowsNW.toDomain(): List<TvShow> =
    this.tvShows.map { tvShow ->
        TvShow(
            tvShow.id,
            tvShow.name,
            tvShow.originalName,
            listOf(),
            tvShow.genreIds,
            listOf(),
            tvShow.firstAirDate,
            null,
            null,
            0,
            0,
            tvShow.originalLanguage,
            tvShow.popularity,
            tvShow.posterPath?.let { Constants.BASE_POSTER_URL + it },
            null,
            tvShow.voteAverage,
            tvShow.voteCount,
            tvShow.overview
        )
    }

fun TvShowDetailsNW.toDomain(): TvShow = TvShow(
    id,
    name,
    originalName,
    languages,
    genres.map { it.id },
    episodeRunTime,
    firstAirDate,
    homepage,
    lastAirDate,
    numberOfEpisodes,
    numberOfSeasons,
    originalLanguage,
    popularity,
    posterPath?.let { Constants.BASE_POSTER_URL + it },
    type,
    voteAverage,
    voteCount,
    overview
)
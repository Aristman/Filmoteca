package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.domain.model.TvShow


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
            tvShow.posterPath,
            null,
            tvShow.voteAverage,
            tvShow.voteCount,
            tvShow.overview
        )
    }
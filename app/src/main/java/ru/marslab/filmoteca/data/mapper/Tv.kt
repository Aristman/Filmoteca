package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.tv.TvShowDetailsNW
import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.domain.repository.Constants


fun TvShowsNW.toDomain(): List<TvShow> =
    this.tvShows.map { tvShow ->
        TvShow(
            id = tvShow.id,
            title = tvShow.name,
            originalTitle = tvShow.originalName,
            adult = false,
            backDrop = tvShow.backdropPath?.let { Constants.BASE_POSTER_URL + it },
            languages = listOf(),
            genres = tvShow.genreIds,
            genreString = "",
            episodeRunTime = listOf(),
            firstAirDate = tvShow.firstAirDate,
            homepage = null,
            lastAirDate = null,
            numberOfEpisodes = 0,
            numberOfSeasons = 0,
            originalLanguage = tvShow.originalLanguage,
            poster = tvShow.posterPath?.let { Constants.BASE_POSTER_URL + it },
            type = null,
            popularity = tvShow.popularity,
            userRating = tvShow.voteAverage,
            voteCount = tvShow.voteCount,
            description = tvShow.overview
        )
    }

fun TvShowDetailsNW.toDomain(): TvShow = TvShow(
    id = id,
    title = name,
    originalTitle = originalName,
    adult = false,
    backDrop = backdropPath?.let { Constants.BASE_POSTER_URL + it },
    languages = languages,
    genres = genres.map { it.id },
    genreString = genres.joinToString(Constants.STRING_SEPARATOR) { it.name },
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    homepage = homepage,
    lastAirDate = lastAirDate,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    originalLanguage = originalLanguage,
    poster = posterPath?.let { Constants.BASE_POSTER_URL + it },
    type = type,
    popularity = popularity,
    userRating = voteAverage,
    voteCount = voteCount,
    description = overview
)
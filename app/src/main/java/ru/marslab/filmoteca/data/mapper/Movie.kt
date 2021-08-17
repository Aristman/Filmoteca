package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.data.model.movies.MoviesNW
import ru.marslab.filmoteca.data.model.tv.TvShowsNW
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.TvShow

fun GuestRatedMoviesNW.toDomain(): List<Movie> =
    this.movies.map { movie ->
        Movie(
            movie.id,
            movie.title,
            movie.originalTitle,
            null,
            movie.genreIds,
            movie.popularity,
            movie.posterPath,
            listOf(),
            movie.releaseDate,
            listOf(),
            movie.originalLanguage,
            movie.rating,
            null,
            movie.overview
        )
    }

fun RequestTokenNW.toDomain(): RequestToken = RequestToken(
    this.requestToken,
    this.expiresAt
)

fun MoviesNW.toDomain(): List<Movie> =
    this.Movies.map {
        Movie(
            id = it.id,
            title = it.title,
            originalTitle = it.originalTitle,
            budget = null,
            genres = it.genreIds,
            popularity = it.popularity,
            poster = it.posterPath,
            productionCompanies = listOf(),
            release = it.releaseDate,
            spokenLanguages = listOf(),
            originalLanguage = it.originalLanguage,
            rating = it.voteAverage,
            timing = null,
            description = it.overview
        )
    }

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
package ru.marslab.filmoteca.data.mapper

import ru.marslab.filmoteca.data.model.auth.RequestTokenNW
import ru.marslab.filmoteca.data.model.guest.GuestRatedMoviesNW
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.RequestToken
import java.text.DateFormat
import java.util.*

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
            movie.rating
        )
    }

fun RequestTokenNW.toDomain(): RequestToken = RequestToken(
    this.requestToken,
    this.expiresAt
)

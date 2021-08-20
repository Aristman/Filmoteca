package ru.marslab.filmoteca.domain.mapper

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TvShow

fun TvShow.toMovie(): Movie = Movie(
    id,
    title,
    originalTitle,
    null,
    genres,
    popularity,
    poster,
    listOf(),
    firstAirDate,
    languages,
    originalLanguage,
    rating,
    null,
    description
)
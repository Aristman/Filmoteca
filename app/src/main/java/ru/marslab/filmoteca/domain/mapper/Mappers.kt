package ru.marslab.filmoteca.domain.mapper

import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.TvShow

fun TvShow.toMovie(): Movie = Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    adult = adult,
    backDrop = backDrop,
    budget = null,
    genres = genres,
    poster = poster,
    productionCompanies = listOf(),
    release = firstAirDate,
    spokenLanguages = languages,
    originalLanguage = originalLanguage,
    popularity = popularity,
    userRating = userRating,
    voteCount = voteCount,
    timing = null,
    description = description
)
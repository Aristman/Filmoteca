package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.model.TvShowDetailUi


fun TvShow.toUiShort(): MovieShortUi =
    MovieShortUi(
        id = id,
        title = title,
        poster = poster,
        releaseDate = firstAirDate,
        userRating = userRating
    )

fun TvShow.toUiFull(): TvShowDetailUi =
    TvShowDetailUi(
        id = id,
        title = title,
        originalTitle = originalTitle,
        adult = adult,
        backDrop = backDrop,
        languages = languages,
        genres = genres,
        genreString = genreString,
        episodeRunTime = episodeRunTime,
        firstAirDate = firstAirDate,
        homepage = homepage,
        lastAirDate = lastAirDate,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originalLanguage = originalLanguage,
        poster = poster,
        type = type,
        popularity = popularity,
        userRating = userRating,
        voteCount = voteCount,
        description = description
    )
package ru.marslab.filmoteca.ui.mapper

import ru.marslab.filmoteca.domain.model.TvShow
import ru.marslab.filmoteca.ui.model.MovieShortUi


fun TvShow.toUiShort(): MovieShortUi =
    MovieShortUi(
        id = id,
        title = title,
        poster = poster,
        releaseDate = firstAirDate,
        userRating = userRating
    )
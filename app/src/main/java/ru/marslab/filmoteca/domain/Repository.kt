package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.ui.guest.model.RatedMoviesUi

interface Repository {
    fun getRatedMovies(): List<RatedMoviesUi>

}
package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.domain.model.Film

interface Repository {
    fun getFilms(): List<Film>

    fun getSize(): Int

    fun getRandomFilm(): Film
}
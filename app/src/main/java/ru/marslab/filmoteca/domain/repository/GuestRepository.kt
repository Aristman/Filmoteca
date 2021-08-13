package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Movie

interface GuestRepository {

    suspend fun createGuestSession(): String?
    suspend fun getGuestRatedMovies(): List<Movie>
}
package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.RequestToken

interface Repository {

    suspend fun createRequestToken(): RequestToken?
}
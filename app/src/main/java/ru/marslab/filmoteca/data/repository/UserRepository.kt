package ru.marslab.filmoteca.data.repository

import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User

interface UserRepository {

    suspend fun createRequestToken(): RequestToken?
    suspend fun createSession(): String?
    suspend fun createSessionWithLogin(user: User): RequestToken?
    suspend fun createGuestSession(): String?
    suspend fun deleteSession(): Boolean
}
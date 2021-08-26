package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Session
import ru.marslab.filmoteca.domain.model.Token
import ru.marslab.filmoteca.domain.model.User

interface UserRepository {

    suspend fun createRequestToken(): Token?
    suspend fun createSession(): Session?
    suspend fun createSessionWithLogin(user: User): Token?
    suspend fun createGuestSession(): Session?
    suspend fun deleteSession(): Boolean
}
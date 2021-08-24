package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Session
import ru.marslab.filmoteca.domain.model.Token
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.UserRepository
import java.util.*

class UserRepositoryImpl(
    private val api: MovieApi,
    private val storage: Storage
) : UserRepository {

    override suspend fun createRequestToken(): Token? {
        val savedToken = storage.getToken()
        return if (savedToken == null || savedToken.expiresAt < Date().time) {
            val requestToken = checkResponse(
                api.createRequestToken(storage.getApikeyV3())
            )
            requestToken?.body()?.let { storage.saveToken(it.toDomain()) }
            requestToken?.body()?.toDomain()
        } else {
            storage.getToken()
        }
    }

    override suspend fun createSession(): Session? {
        return null
    }

    override suspend fun createSessionWithLogin(user: User): Token? {
        return storage.getToken()?.let {token ->
            val response = checkResponse(
                api.createSessionWithLogin(
                    storage.getApikeyV3(),
                    user.name,
                    user.password,
                    token.id
                )
            )
            response?.body()?.let {
                if (it.success) {
                    storage.saveToken(it.toDomain())
                    storage.saveUser(user)
                    it.toDomain()
                } else {
                    null
                }
            }
        }
    }

    override suspend fun createGuestSession(): Session? {
        val savedGuestSession = storage.getGuestSession()
        return if (savedGuestSession == null || savedGuestSession.expiresAt < Date().time) {
            val guestSession = checkResponse(
                api.createGuestSessionId(storage.getApikeyV3())
            )
            guestSession?.body()?.let { storage.saveGuestSession(it.toDomain()) }
            guestSession?.body()?.toDomain()
        } else {
            storage.getGuestSession()
        }
    }

    override suspend fun deleteSession(): Boolean {
        return false
    }
}
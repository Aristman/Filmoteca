package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Store
import ru.marslab.filmoteca.domain.repository.UserRepository
import ru.marslab.filmoteca.ui.util.logMessage

class UserRepositoryImpl(private val api: MovieApi, private val store: Store) : UserRepository {

    override suspend fun createRequestToken(): RequestToken? {
        val createdRequestToken = api.createRequestToken(store.apiKeyV3)
        if (createdRequestToken.isSuccessful) {
            logMessage(createdRequestToken.body().toString())
            store.requestToken = createdRequestToken.body()?.toDomain()
        }
        return store.requestToken
    }

    override suspend fun createSession(): String? {
        return null
    }

    override suspend fun createSessionWithLogin(user: User): RequestToken? {
        return store.requestToken?.let {
            val response =
                api.createSessionWithLogin(store.apiKeyV3, user.name, user.password, it.token)
            if (response.isSuccessful && response.body()?.success == true) {
                logMessage(response.body().toString())
                store.requestToken = response.body()?.toDomain()
                store.user = user
                store.requestToken
            } else {
                null
            }
        }
    }

    override suspend fun createGuestSession(): String? {
        store.sessionId = try {
            val guestSessionId = api.createGuestSessionId(store.apiKeyV3)
            if (guestSessionId.isSuccessful) {
                logMessage(guestSessionId.body().toString())
                guestSessionId.body()?.guestSessionId
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
        return store.sessionId
    }

    override suspend fun deleteSession(): Boolean {
        return false
    }
}
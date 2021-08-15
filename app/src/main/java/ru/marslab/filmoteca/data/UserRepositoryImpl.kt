package ru.marslab.filmoteca.data

import android.util.Log
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.UserRepository
import ru.marslab.filmoteca.domain.util.Constants.LOG_TAG

class UserRepositoryImpl(
    private val api: MovieApi,
) : UserRepository {

    override suspend fun createRequestToken(): RequestToken? {
        val createdRequestToken = api.createRequestToken(Store.apiKeyV3)
        if (createdRequestToken.isSuccessful) {
            Log.d(LOG_TAG, createdRequestToken.body().toString())
            Store.requestToken = createdRequestToken.body()?.toDomain()
        }
        return Store.requestToken
    }

    override suspend fun createSession(): String? {
        return null
    }

    override suspend fun createSessionWithLogin(user: User): RequestToken? {
        return Store.requestToken?.let {
            val response =
                api.createSessionWithLogin(Store.apiKeyV3, user.name, user.password, it.token)
            if (response.isSuccessful && response.body()?.success == true) {
                Log.d(LOG_TAG, response.body().toString())
                Store.requestToken = response.body()?.toDomain()
                Store.user = user
                Store.requestToken
            } else {
                null
            }
        }
    }

    override suspend fun deleteSession(): Boolean {
        return false
    }
}
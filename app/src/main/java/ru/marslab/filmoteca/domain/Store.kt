package ru.marslab.filmoteca.domain

import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User

object Store {
    var sessionId: String? = null
    var requestToken: RequestToken? = null
    var user: User? = null

    var adult: Boolean = false

    val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4

}
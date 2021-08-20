package ru.marslab.filmoteca.data

import android.content.SharedPreferences
import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Store

class StoreImpl(sharedPreferences: SharedPreferences) : Store {
    override var sessionId: String? = null
    override var requestToken: RequestToken? = null
    override var user: User? = null
    override var adult: Boolean = false

    override val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    override val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4
}
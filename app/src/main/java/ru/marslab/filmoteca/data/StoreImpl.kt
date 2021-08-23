package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Store

class StoreImpl : Store {
    override var sessionId: String? = null
    override var requestToken: RequestToken? = null
    override var user: User? = null
    override var adult: Boolean = false
    override var languages: List<Language> = listOf()

    override val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    override val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4
}
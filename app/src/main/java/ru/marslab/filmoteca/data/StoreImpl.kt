package ru.marslab.filmoteca.data

import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.*
import ru.marslab.filmoteca.data.repository.Store

class StoreImpl : Store {
    override var sessionId: String? = null
    override var requestToken: RequestToken? = null
    override var user: User? = null
    override var adult: Boolean = false

    override var languages: List<Language> = listOf()
    override var countries: List<Country> = listOf()
    override var timeZones: List<TimeZone> = listOf()

    override val apiKeyV3: String
        get() = BuildConfig.API_KEY_V3
    override val apiKeyV4: String
        get() = BuildConfig.API_KEY_V4
}
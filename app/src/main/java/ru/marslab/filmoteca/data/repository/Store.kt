package ru.marslab.filmoteca.data.repository

import ru.marslab.filmoteca.domain.model.*

interface Store {
    companion object {
        const val START_DELAY = 1000L
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        const val SETTING_ADULT = "adult"
    }

    var sessionId: String?
    var requestToken: RequestToken?
    var user: User?

    var countries: List<Country>
    var languages: List<Language>
    var timeZones: List<TimeZone>

    var adult: Boolean

    val apiKeyV3: String
    val apiKeyV4: String
}
package ru.marslab.filmoteca.domain.repository

import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.RequestToken
import ru.marslab.filmoteca.domain.model.User

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
    var languages: List<Language>

    var adult: Boolean

    val apiKeyV3: String
    val apiKeyV4: String
}
package ru.marslab.filmoteca.domain.repository

object Constants {
    const val STRING_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss z"
    const val START_DELAY = 1000L
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"

    const val GPS_REFRESH_PERIOD = 60000L
    const val GPS_DISTANCE = 100f

    const val SETTING_ADULT = "adult"
    const val SETTING_LANGUAGE = "setting_language"
    const val SETTING_TIMEZONE = "setting_region"

    const val TOKEN_ID = "token_id"
    const val TOKEN_EXPIRES = "token_expires"
    const val GUEST_SESSION_ID = "guest_session_id"
    const val GUEST_SESSION_EXPIRES = "guest_session_expires"
    const val USER_NAME = "user_name"
    const val USER_PASSWORD = "user_password"

    const val USER_EXTRA = "login_user"
    const val GUEST_LOGIN_SUCCESSFUL = "guest_login_successful"
    const val USER_LOGIN_SUCCESSFUL = "user_login_successful"
    const val LOGIN_ERROR = "login_error"
    const val TOKEN_ERROR = "token_error"
    const val LOGIN_INTENT_FILTER = "login_intent_filter"
}
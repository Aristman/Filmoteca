package ru.marslab.filmoteca.data

import android.content.SharedPreferences
import ru.marslab.filmoteca.BuildConfig
import ru.marslab.filmoteca.domain.model.Session
import ru.marslab.filmoteca.domain.model.Token
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Storage

private const val TOKEN_ID = "token_id"
private const val TOKEN_EXPIRES = "token_expires"
private const val GUEST_SESSION_ID = "guest_session_id"
private const val GUEST_SESSION_EXPIRES = "guest_session_expires"
private const val USER_NAME = "user_name"
private const val USER_PASSWORD = "user_password"
private const val SETTING_ADULT = "setting_adult"
private const val SETTING_LANGUAGE = "setting_language"
private const val SETTING_REGION = "setting_region"


class StorageImpl(private val sharedPreferences: SharedPreferences) : Storage {

    override fun getGuestSession(): Session? {
        val sessionId = sharedPreferences.getString(GUEST_SESSION_ID, null)
        val sessionExpires = sharedPreferences.getLong(GUEST_SESSION_EXPIRES, 0L)
        return if (sessionId.isNullOrBlank()) {
            null
        } else {
            Session(sessionId, sessionExpires)
        }
    }

    override fun saveGuestSession(session: Session) {
        sharedPreferences.edit()
            .putString(GUEST_SESSION_ID, session.id)
            .putLong(GUEST_SESSION_EXPIRES, session.expiresAt)
            .apply()
    }

    override fun getToken(): Token? {
        val tokenId = sharedPreferences.getString(TOKEN_ID, null)
        val tokenExpires = sharedPreferences.getLong(TOKEN_EXPIRES, 0L)
        return if (tokenId.isNullOrBlank()) {
            null
        } else {
            Token(tokenId, tokenExpires)
        }
    }

    override fun saveToken(token: Token) {
        sharedPreferences.edit()
            .putString(TOKEN_ID, token.id)
            .putLong(TOKEN_EXPIRES, token.expiresAt)
            .apply()
    }

    override fun getUser(): User? {
        val name = sharedPreferences.getString(USER_NAME, null)
        val password = sharedPreferences.getString(USER_PASSWORD, null)
        return if (name.isNullOrBlank() || password.isNullOrBlank()) {
            null
        } else {
            User(name, password)
        }
    }

    override fun saveUser(user: User) {
        sharedPreferences.edit()
            .putString(USER_NAME, user.name)
            .putString(USER_PASSWORD, user.password)
            .apply()
    }

    override fun getApikeyV3(): String = BuildConfig.API_KEY_V3

    override fun getApikeyV4(): String = BuildConfig.API_KEY_V4

    override val adult: Boolean
        get() = sharedPreferences.getBoolean(SETTING_ADULT, false)
    override val language: String
        get() = sharedPreferences.getString(SETTING_LANGUAGE, "") ?: ""
    override val region: String
        get() = sharedPreferences.getString(SETTING_REGION, "") ?: ""

    override fun saveSettingAdult(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(SETTING_ADULT, value)
            .apply()
    }

    override fun saveSettingLanguage(language: String) {
        sharedPreferences.edit()
            .putString(SETTING_LANGUAGE, language)
            .apply()
    }

    override fun saveSettingRegion(region: String) {
        sharedPreferences.edit()
            .putString(SETTING_REGION, region)
            .apply()
    }
}
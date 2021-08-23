package ru.marslab.filmoteca.ui.login

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.data.repository.Store
import ru.marslab.filmoteca.data.repository.UserRepository
import javax.inject.Inject

const val USER_EXTRA = "login_user"
const val GUEST_LOGIN_SUCCESSFUL = "guest_login_successful"
const val USER_LOGIN_SUCCESSFUL = "user_login_successful"
const val LOGIN_ERROR = "login_error"
const val TOKEN_ERROR = "token_error"
const val LOGIN_INTENT_FILTER = "login_intent_filter"

@AndroidEntryPoint
class LoginService(name: String = "GuestLoginService") : IntentService(name) {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var store: Store

    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<User>(USER_EXTRA)?.let {
            userLogin(it)
            return
        }
        guestLogin()
    }

    private fun guestLogin() {
        val loginIntent = Intent(LOGIN_INTENT_FILTER)
        val guestSession = runBlocking {
            userRepository.createGuestSession()
        }
        if (guestSession == null) {
            loginIntent.putExtra(LOGIN_ERROR, true)
        } else {
            store.sessionId = guestSession
            loginIntent.putExtra(GUEST_LOGIN_SUCCESSFUL, true)
        }
        LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(loginIntent)

    }

    private fun userLogin(user: User) {
        val loginIntent = Intent(LOGIN_INTENT_FILTER)
        val requestToken = runBlocking {
            userRepository.createRequestToken()
        }
        if (requestToken == null) {
            loginIntent.putExtra(TOKEN_ERROR, true)
        } else {
            val userLoginToken = runBlocking {
                userRepository.createSessionWithLogin(user)
            }
            if (userLoginToken == null) {
                loginIntent.putExtra(LOGIN_ERROR, true)
            } else {
                loginIntent.putExtra(USER_LOGIN_SUCCESSFUL, true)
            }
        }
        LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(loginIntent)
    }
}
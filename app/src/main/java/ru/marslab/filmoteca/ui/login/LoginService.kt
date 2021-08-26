package ru.marslab.filmoteca.ui.login

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.UserRepository
import javax.inject.Inject


@AndroidEntryPoint
class LoginService(name: String = "GuestLoginService") : IntentService(name) {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var storage: Storage

    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<User>(Constants.USER_EXTRA)?.let {
            userLogin(it)
            return
        }
        guestLogin()
    }

    private fun guestLogin() {
        val loginIntent = Intent(Constants.LOGIN_INTENT_FILTER)
        val guestSession = runBlocking {
            userRepository.createGuestSession()
        }
        if (guestSession == null) {
            loginIntent.putExtra(Constants.LOGIN_ERROR, true)
        } else {
            storage.saveGuestSession(guestSession)
            loginIntent.putExtra(Constants.GUEST_LOGIN_SUCCESSFUL, true)
        }
        LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(loginIntent)

    }

    private fun userLogin(user: User) {
        val loginIntent = Intent(Constants.LOGIN_INTENT_FILTER)
        val requestToken = runBlocking {
            userRepository.createRequestToken()
        }
        if (requestToken == null) {
            loginIntent.putExtra(Constants.TOKEN_ERROR, true)
        } else {
            val userLoginToken = runBlocking {
                userRepository.createSessionWithLogin(user)
            }
            if (userLoginToken == null) {
                loginIntent.putExtra(Constants.LOGIN_ERROR, true)
            } else {
                loginIntent.putExtra(Constants.USER_LOGIN_SUCCESSFUL, true)
            }
        }
        LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(loginIntent)
    }
}
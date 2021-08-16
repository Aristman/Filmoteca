package ru.marslab.filmoteca.ui.login

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import ru.marslab.filmoteca.domain.Store
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.GuestRepository
import javax.inject.Inject

const val USER_EXTRA = "login_user"
const val GUEST_LOGIN_SUCCESSFUL = "guest_login_successful"
const val USER_LOGIN_SUCCESSFUL = "user_login_successful"
const val LOGIN_ERROR = "login_error"
const val LOGIN_INTENT_FILTER = "login_intent_filter"

@AndroidEntryPoint
class LoginService(name: String = "GuestLoginService") : IntentService(name) {

    @Inject
    lateinit var guestRepository: GuestRepository

    private val loginIntent = Intent(LOGIN_INTENT_FILTER)

    override fun onHandleIntent(intent: Intent?) {
        intent?.getParcelableExtra<User>(USER_EXTRA)?.let {
            userLogin(it)
            return
        }
        guestLogin()
    }

    private fun guestLogin() {
        runBlocking {
            val guestSession = guestRepository.createGuestSession()
            guestSession?.let {
                Store.sessionId = it
                LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(
                    loginIntent.putExtra(GUEST_LOGIN_SUCCESSFUL, true)
                )
                return@runBlocking
            }
            LocalBroadcastManager.getInstance(this@LoginService).sendBroadcast(
                loginIntent.putExtra(LOGIN_ERROR, true)
            )
        }
    }

    private fun userLogin(user: User) {
        TODO("Not yet implemented")
    }
}
package ru.marslab.filmoteca.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.repository.UserRepository
import ru.marslab.filmoteca.domain.util.Constants
import ru.marslab.filmoteca.ui.util.OnEvent
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOADING_GUEST_SESSION = "Ошибка соединение. Не получен ключ сессии"
private const val ERROR_LOADING_USER_SESSION = "Ошибка соединение. Не верны логин или пароль"


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val guestRepository: GuestRepository
) : ViewModel() {

    private var isUserSessionConnected: Boolean = false

    private var _userSession: MutableLiveData<ViewState> = MutableLiveData()
    val userSession: LiveData<ViewState>
        get() = _userSession

    fun userLogin(user: User) {
        _userSession.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = userRepository.createSessionWithLogin(user)
                token?.let {
                    _userSession.postValue(ViewState.Successful(true))
                    isUserSessionConnected = true
                    return@launch
                }
                _userSession.postValue(ViewState.LoadError(ERROR_LOADING_USER_SESSION))
            } catch (e: java.lang.Exception) {
                _userSession.postValue(ViewState.LoadError(ERROR_LOADING_USER_SESSION))
            }
        }
    }
}
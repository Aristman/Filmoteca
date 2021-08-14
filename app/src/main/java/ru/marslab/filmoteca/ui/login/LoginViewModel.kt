package ru.marslab.filmoteca.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.domain.repository.GuestRepository
import ru.marslab.filmoteca.domain.util.Constants
import ru.marslab.filmoteca.ui.util.OnEvent
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOADING_SESSION = "Ошибка соединение. Не получен ключ сессии"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val guestRepository: GuestRepository
) : ViewModel() {
    private var isGuestSessionConnected: Boolean = false
    private var _guestSession: MutableLiveData<ViewState> = MutableLiveData()
    val guestSession: LiveData<ViewState>
        get() = _guestSession

    fun guestSessionConnect() {
        _guestSession.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (step in 0..Constants.MAX_REQUEST_COUNT) {
                    val sessionId = guestRepository.createGuestSession()
                    sessionId?.let {
                        _guestSession.postValue(ViewState.Successful(OnEvent(true)))
                        isGuestSessionConnected = true
                        return@launch
                    }
                }
                _guestSession.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            } catch (e: Exception) {
                _guestSession.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            }
        }
    }

    fun isNotConnected(): Boolean = !isGuestSessionConnected
}
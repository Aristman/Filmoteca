package ru.marslab.filmoteca.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.util.Constants
import ru.marslab.filmoteca.ui.util.OnEvent
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOADING_SESSION = "Ошибка соединение. Не получен ключ сессии"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var isSessionConnected: Boolean = false
    private var _sessionConnect: MutableLiveData<ViewState> = MutableLiveData()
    val sessionConnect: LiveData<ViewState>
        get() = _sessionConnect

    fun sessionConnect() {
        _sessionConnect.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (step in 0..Constants.MAX_REQUEST_COUNT) {
                    val sessionId = repository.takeSessionId()
                    sessionId?.let {
                        _sessionConnect.postValue(ViewState.Successful(OnEvent(true)))
                        isSessionConnected = true
                        return@launch
                    }
                }
                _sessionConnect.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            } catch (e: Exception) {
                _sessionConnect.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            }
        }
    }
    
    fun isNotConnected(): Boolean = !isSessionConnected
    
    fun getSessionId(): String? = repository.sessionId
}
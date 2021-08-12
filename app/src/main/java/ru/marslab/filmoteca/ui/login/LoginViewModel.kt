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
    private var sessionId: String = ""
    private var _isSessionConnected: MutableLiveData<ViewState> = MutableLiveData()
    val isSessionConnected: LiveData<ViewState>
        get() = _isSessionConnected

    fun sessionConnect() {
        _isSessionConnected.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (step in 0..Constants.MAX_REQUEST_COUNT) {
                    val tempSessionId = repository.getSessionId()
                    tempSessionId?.let {
                        sessionId = it
                        _isSessionConnected.postValue(ViewState.Successful(OnEvent(true)))
                        return@launch
                    }
                }
                _isSessionConnected.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            } catch (e: Exception) {
                _isSessionConnected.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            }

        }
    }

    fun getSessionId(): String = sessionId
}
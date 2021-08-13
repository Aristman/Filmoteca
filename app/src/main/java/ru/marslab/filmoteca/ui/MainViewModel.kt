package ru.marslab.filmoteca.ui

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

private const val ERROR_LOADING_SESSION = "Ошибка загрузки токена"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var isTokenPresent: Boolean = false
    private var _token: MutableLiveData<ViewState> = MutableLiveData()
    val token: LiveData<ViewState>
        get() = _token

    fun requestToken() {
        _token.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                for (step in 0..Constants.MAX_REQUEST_COUNT) {
                    val newToken = repository.createRequestToken()
                    newToken?.let {
                        _token.postValue(ViewState.Successful(OnEvent(true)))
                        isTokenPresent = true
                        return@launch
                    }
                }
                _token.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            } catch (e: Exception) {
                _token.postValue(ViewState.LoadError(ERROR_LOADING_SESSION))
            }
        }
    }

    fun isNoTokenPresent(): Boolean = !isTokenPresent

}
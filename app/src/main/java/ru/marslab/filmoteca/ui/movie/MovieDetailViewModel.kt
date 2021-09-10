package ru.marslab.filmoteca.ui.movie


import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.ui.mapper.toUiFull
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOAD_DATA = "Ошибка загрузки данных по фильму"

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val databaseRepository: DatabaseRepository,
    private val storage: Storage,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    private var _movieDetail: MutableLiveData<ViewState> = MutableLiveData()
    val movieDetail: LiveData<ViewState>
        get() = _movieDetail

    private var _movieComment: MutableLiveData<String> = MutableLiveData()
    val movieComment: LiveData<String>
        get() = _movieComment

    private var _moviePeople: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Init)
    val moviePeople: StateFlow<ViewState>
        get() = _moviePeople

    fun getMovieDetailInfo(id: Int) {
        viewModelScope.launch(dispatchers.io) {
            val settingLanguage = storage.getSettingLanguage()
            val movie = movieRepository.getMovieDetails(id, language = settingLanguage)
            if (movie == null) {
                _movieDetail.postValue(ViewState.LoadError(NetworkErrorException(ERROR_LOAD_DATA)))
            } else {
                _movieDetail.postValue(ViewState.Successful(movie.toUiFull()))
            }
            val comment = databaseRepository.getMovieComment(id)
            _movieComment.postValue(comment ?: "")
        }
    }

    fun saveMovieDataToHistory(movieId: Int, time: Long, comment: CharSequence) {
        viewModelScope.launch(dispatchers.io) {
            databaseRepository.saveMovieHistory(movieId, time, comment.toString())
        }
    }

    fun getMoviePeople(movieId: Int) {
        viewModelScope.launch(dispatchers.io) {
            try {
                val settingLanguage = storage.getSettingLanguage()
                val people = movieRepository.getMoviePeople(movieId, language = settingLanguage)
                if (people == null) {
                    _moviePeople.emit(ViewState.LoadError(NetworkErrorException(ERROR_LOAD_DATA)))
                } else {
                    _moviePeople.emit(ViewState.Successful(people))
                }
            } catch (e: NetworkErrorException) {
                _moviePeople.emit(
                    ViewState.LoadError(NetworkErrorException(Constants.NETWORK_TROUBLE).apply {
                        stackTrace = e.stackTrace
                    })
                )
            }
        }
    }
}
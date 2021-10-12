package ru.marslab.filmoteca.ui.movie

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.ui.mapper.toUiShort
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val storage: Storage,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _popularMovies: MutableLiveData<ViewState> = MutableLiveData(ViewState.Init)
    val popularMovies: LiveData<ViewState>
        get() = _popularMovies


    private var _topRatedMovies: MutableLiveData<ViewState> = MutableLiveData(ViewState.Init)
    val topRatedMovies: LiveData<ViewState>
        get() = _topRatedMovies

    val popularMoviesFlow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        movieRepository.getPopularMoviesPagingSource()
    }.flow
        .cachedIn(viewModelScope)

    fun loadPopularMovies() {
        _popularMovies.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val settingLanguage = storage.getSettingLanguage()
            val settingTimeZone = storage.getSettingTimeZone()
            val listMovies = movieRepository.getPopularMovies(
                language = settingLanguage,
                region = settingTimeZone
            )
            if (listMovies == null) {
                _popularMovies.postValue(ViewState.LoadError(NetworkErrorException(Constants.ERROR_LOAD_MOVIES)))
            } else {
                _popularMovies.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }


    fun loadTopRatedMovies() {
        _topRatedMovies.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val settingLanguage = storage.getSettingLanguage()
            val settingTimeZone = storage.getSettingTimeZone()
            val listMovies = movieRepository.getTopRatedMovies(
                language = settingLanguage,
                region = settingTimeZone
            )
            if (listMovies == null) {
                _topRatedMovies.postValue(ViewState.LoadError(NetworkErrorException(Constants.ERROR_LOAD_MOVIES)))
            } else {
                _topRatedMovies.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }
}

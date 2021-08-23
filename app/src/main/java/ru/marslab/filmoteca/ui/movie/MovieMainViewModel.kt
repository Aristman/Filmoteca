package ru.marslab.filmoteca.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.repository.MovieRepository
import ru.marslab.filmoteca.data.repository.TvRepository
import ru.marslab.filmoteca.ui.mapper.toUiShort
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOAD_POPULAR_MOVIES =
    "Ошибка загрузки списка фильмов: пустой список от сервера"

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _popularMovies: MutableLiveData<ViewState> = MutableLiveData()
    val popularMovies: LiveData<ViewState>
        get() = _popularMovies

    private var _popularTvShows: MutableLiveData<ViewState> = MutableLiveData()
    val popularTvShows: LiveData<ViewState>
        get() = _popularTvShows

    private var _topRatedMovies: MutableLiveData<ViewState> = MutableLiveData()
    val topRatedMovies: LiveData<ViewState>
        get() = _topRatedMovies


    fun loadPopularMovies() {
        _popularMovies.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val listMovies = movieRepository.getPopularMovies()?.toDomain()
            if (listMovies == null) {
                _popularMovies.postValue(ViewState.LoadError(ERROR_LOAD_POPULAR_MOVIES))
            } else {
                _popularMovies.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }

    fun loadPopularTvShows() {
        _popularTvShows.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val listMovies = tvRepository.getPopularTvShows()?.toDomain()
            if (listMovies == null) {
                _popularTvShows.postValue(ViewState.LoadError(ERROR_LOAD_POPULAR_MOVIES))
            } else {
                _popularTvShows.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }

    fun loadTopRatedMovies() {
        _topRatedMovies.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val listMovies = movieRepository.getTopRatedMovies()?.toDomain()
            if (listMovies == null) {
                _topRatedMovies.postValue(ViewState.LoadError(ERROR_LOAD_POPULAR_MOVIES))
            } else {
                _topRatedMovies.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }
}

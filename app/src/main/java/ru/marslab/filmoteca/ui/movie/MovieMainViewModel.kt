package ru.marslab.filmoteca.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.MovieRepository
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {


    val popularMovies = Pager(
        PagingConfig(pageSize = 20)
    ) {
        movieRepository.getPopularMoviesPagingSource()
    }.flow
        .cachedIn(viewModelScope)

    val ratedMovies = Pager(
        PagingConfig(pageSize = 20)
    ) {
        movieRepository.getRatedMoviesPagingSource()
    }.flow
        .cachedIn(viewModelScope)


//    fun loadTopRatedMovies() {
//        _topRatedMovies.value = ViewState.Loading
//        viewModelScope.launch(dispatchers.io) {
//            val settingLanguage = storage.getSettingLanguage()
//            val settingTimeZone = storage.getSettingTimeZone()
//            val listMovies = movieRepository.getTopRatedMovies(
//                language = settingLanguage,
//                region = settingTimeZone
//            )
//            if (listMovies == null) {
//                _topRatedMovies.postValue(ViewState.LoadError(NetworkErrorException(Constants.ERROR_LOAD_MOVIES)))
//            } else {
//                _topRatedMovies.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
//            }
//        }
//    }
}

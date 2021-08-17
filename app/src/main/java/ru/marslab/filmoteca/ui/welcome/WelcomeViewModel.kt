package ru.marslab.filmoteca.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val DATA_NOT_INIT = "Данные не инициализированы"

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    private var _popularMovies: MutableLiveData<ViewState>? = null

    val popularMovies: LiveData<ViewState>
        get() = checkNotNull(_popularMovies) { DATA_NOT_INIT }
    private var _popularTvShows: MutableLiveData<ViewState>? = null

    val popularTvShows: LiveData<ViewState>
        get() = checkNotNull(_popularTvShows) { DATA_NOT_INIT }
    private var _recommendationMovies: MutableLiveData<ViewState>? = null

    val recommendationMovies: LiveData<ViewState>
        get() = checkNotNull(_recommendationMovies) { DATA_NOT_INIT }


    fun loadPopularMovies() {
        TODO("Not yet implemented")
    }

    fun loadPopularTvShows() {
        TODO("Not yet implemented")
    }

    fun loadRecommendationMovies() {
        TODO("Not yet implemented")
    }
}

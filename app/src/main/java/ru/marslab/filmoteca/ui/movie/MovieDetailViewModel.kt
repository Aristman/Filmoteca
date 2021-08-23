package ru.marslab.filmoteca.ui.movie


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.ui.mapper.toUiFull
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOAD_DATA = "Ошибка загрузки данных по фильму"

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val databaseRepository: DatabaseRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    private var _movieDetail: MutableLiveData<ViewState> = MutableLiveData()
    val movieDetail: LiveData<ViewState>
        get() = _movieDetail
    private var _movieComment: MutableLiveData<String> = MutableLiveData()
    val movieComment: LiveData<String>
        get() = _movieComment

    fun getMovieDetailInfo(id: Int) {
        viewModelScope.launch(dispatchers.io) {
            val movie = movieRepository.getMovieDetails(id)
            if (movie == null) {
                _movieDetail.postValue(ViewState.LoadError(ERROR_LOAD_DATA))
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
}
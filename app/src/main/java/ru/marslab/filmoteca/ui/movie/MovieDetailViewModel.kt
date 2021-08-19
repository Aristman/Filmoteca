package ru.marslab.filmoteca.ui.movie


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.ui.mapper.toUiFull
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOAD_DATA = "Ошибка загрузки данных по фильму"

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private var _movieDetail: MutableLiveData<ViewState> = MutableLiveData()
    val movieDetail: LiveData<ViewState>
        get() = _movieDetail

    fun getMovieDetailInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = movieRepository.getMovieDetails(id)?.toDomain()
            if (movie == null) {
                _movieDetail.postValue(ViewState.LoadError(ERROR_LOAD_DATA))
            } else {
                _movieDetail.postValue(ViewState.Successful(movie.toUiFull()))
            }

        }
    }
}
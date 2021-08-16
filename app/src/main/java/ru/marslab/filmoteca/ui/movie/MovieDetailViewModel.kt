package ru.marslab.filmoteca.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.MovieRepository
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private var _movieDetail: MutableLiveData<MovieDetailUi> = MutableLiveData()
    val movieDetail: LiveData<MovieDetailUi>
        get() = _movieDetail

    fun getMovieDetailInfo(id: Int) {
//        _movieDetail.value = repository.getMovieDetail(id)!!
    }
}
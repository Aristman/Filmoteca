package ru.marslab.filmoteca.ui.guest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.ui.mapper.toUi
import ru.marslab.filmoteca.ui.model.MovieShortUi
import javax.inject.Inject

@HiltViewModel
class GuestViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _ratedMovies: MutableLiveData<List<MovieShortUi>> = MutableLiveData()
    val ratedMovies: LiveData<List<MovieShortUi>>
        get() = _ratedMovies


    fun getRatedMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val guestRatedMovies = repository.getGuestRatedMovies()
            _ratedMovies.postValue(guestRatedMovies.toUi())
        }
    }
}

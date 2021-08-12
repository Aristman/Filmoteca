package ru.marslab.filmoteca.ui.guest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.domain.Repository
import ru.marslab.filmoteca.domain.util.Constants.LOAD_DELAY
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GuestViewModel @Inject constructor(
    private val repository: Repository,
    private val context: Context
) : ViewModel() {
    private var _ratedMovies: MutableLiveData<ViewState> = MutableLiveData()

    val ratedMovies: LiveData<ViewState>
        get() = _ratedMovies


    fun getRatedMoviesList() {
        _ratedMovies.value = ViewState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(LOAD_DELAY)
            if (Random.nextBoolean()) {
                _ratedMovies.postValue(
                    ViewState.Successful(repository.getRatedMovies())
                )
            } else {
                _ratedMovies.postValue(
                    ViewState.LoadError(context.getString(R.string.load_list_movies_error))
                )
            }
        }
    }
}

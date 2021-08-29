package ru.marslab.filmoteca.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.TvRepository
import ru.marslab.filmoteca.ui.mapper.toUiShort
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

@HiltViewModel
class TvMainViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    private val storage: Storage,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _popularTvShows: MutableLiveData<ViewState> = MutableLiveData()
    val popularTvShows: LiveData<ViewState>
        get() = _popularTvShows

    fun loadPopularTvShows() {
        _popularTvShows.value = ViewState.Loading
        viewModelScope.launch(dispatchers.io) {
            val settingLanguage = storage.getSettingLanguage()
            val listMovies = tvRepository.getPopularTvShows(settingLanguage)
            if (listMovies == null) {
                _popularTvShows.postValue(ViewState.LoadError(Constants.ERROR_LOAD_TV))
            } else {
                _popularTvShows.postValue(ViewState.Successful(listMovies.map { it.toUiShort() }))
            }
        }
    }
}
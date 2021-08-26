package ru.marslab.filmoteca.ui.tv


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.Storage
import ru.marslab.filmoteca.domain.repository.TvRepository
import ru.marslab.filmoteca.ui.mapper.toUiFull
import ru.marslab.filmoteca.ui.util.ViewState
import javax.inject.Inject

private const val ERROR_LOAD_DATA = "Ошибка загрузки данных по сериалу"

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository,
    private val storage: Storage,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    private var _tvDetail: MutableLiveData<ViewState> = MutableLiveData()
    val tvDetail: LiveData<ViewState>
        get() = _tvDetail

    fun getTvDetailInfo(id: Int) {
        viewModelScope.launch(dispatchers.io) {
            val settingLanguage = storage.getSettingLanguage()
            val tvShow = tvRepository.getTvDetailInfo(id, language = settingLanguage)
            if (tvShow == null) {
                _tvDetail.postValue(ViewState.LoadError(ERROR_LOAD_DATA))
            } else {
                _tvDetail.postValue(ViewState.Successful(tvShow.toUiFull()))
            }

        }
    }
}
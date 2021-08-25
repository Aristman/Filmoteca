package ru.marslab.filmoteca.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.Storage
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storage: Storage,
    private val databaseRepository: DatabaseRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _languagesList: MutableLiveData<List<String>> = MutableLiveData()
    val languagesList: LiveData<List<String>>
        get() = _languagesList

    var adult: Boolean
        get() = storage.adult
        set(value) {
            storage.saveSettingAdult(value)
        }

    var language: String
        get() = storage.language
        set(value) {
            storage.saveSettingLanguage(value)
        }

    fun getLanguagesList() {
        viewModelScope.launch(dispatchers.io) {
            val languages = databaseRepository.getLanguages()
            if (languages.isNotEmpty()) {
                _languagesList.postValue(languages.map { it.name })
            }
        }
    }
}
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

    private var _timeZonesList: MutableLiveData<List<String>> = MutableLiveData()
    val timeZonesList: LiveData<List<String>>
        get() = _timeZonesList

    private var _adult: MutableLiveData<Boolean> = MutableLiveData()
    val adult: LiveData<Boolean>
        get() = _adult


    fun getAdultStorageValue(): Boolean = storage.adult

    fun setAdult(adult: Boolean) {
        _adult.value = adult
        storage.saveSettingAdult(adult)
    }

    fun getStorageLanguage(): String = storage.language

    fun setLanguage(languageName: String) {
        storage.saveSettingLanguage(languageName)
    }

    fun getStorageTimeZone(): String = storage.timeZone

    fun setTimeZone(timeZoneName: String) {
        storage.saveSettingTimeZone(timeZoneName)
    }

    fun getLanguagesList() {
        viewModelScope.launch(dispatchers.io) {
            val languages = databaseRepository.getLanguages()
            if (languages.isNotEmpty()) {
                _languagesList.postValue(languages.map { it.name })
            }
        }
    }

    fun getTimeZonesList() {
        viewModelScope.launch(dispatchers.io) {
            val timeZones = databaseRepository.getTimeZones()
            if (timeZones.isNotEmpty()) {
                _timeZonesList.postValue(timeZones.map { it.names.first() })
            }
        }
    }
}
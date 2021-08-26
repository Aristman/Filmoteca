package ru.marslab.filmoteca.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.Storage
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storage: Storage,
    private val databaseRepository: DatabaseRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _languagesList: MutableLiveData<List<Language>> = MutableLiveData()
    val languagesList: LiveData<List<Language>>
        get() = _languagesList

    private var _timeZonesList: MutableLiveData<List<TimeZone>> = MutableLiveData()
    val timeZonesList: LiveData<List<TimeZone>>
        get() = _timeZonesList

    private var _adult: MutableLiveData<Boolean> = MutableLiveData()
    val adult: LiveData<Boolean>
        get() = _adult


    fun getAdultStorageValue(): Boolean = storage.getSettingAdult()

    fun setAdult(adult: Boolean) {
        _adult.value = adult
        storage.saveSettingAdult(adult)
    }

    fun getStorageLanguage(): Language? = storage.getSettingLanguage()

    fun setLanguage(languageName: Language) {
        storage.saveSettingLanguage(languageName)
    }

    fun getStorageTimeZone(): TimeZone? = storage.getSettingTimeZone()

    fun setTimeZone(timeZoneName: TimeZone) {
        storage.saveSettingTimeZone(timeZoneName)
    }

    fun getLanguagesList() {
        viewModelScope.launch(dispatchers.io) {
            val languages = databaseRepository.getLanguages()
            if (languages.isNotEmpty()) {
                _languagesList.postValue(languages)
            }
        }
    }

    fun getTimeZonesList() {
        viewModelScope.launch(dispatchers.io) {
            val timeZones = databaseRepository.getTimeZones()
            if (timeZones.isNotEmpty()) {
                _timeZonesList.postValue(timeZones)
            }
        }
    }
}
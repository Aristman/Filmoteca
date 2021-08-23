package ru.marslab.filmoteca.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.Constants.SETTING_ADULT
import ru.marslab.filmoteca.domain.repository.SettingsRepository
import ru.marslab.filmoteca.domain.repository.Store
import ru.marslab.filmoteca.ui.model.LoadConfigsState
import ru.marslab.filmoteca.ui.model.LoadConfigsState.LoadError
import ru.marslab.filmoteca.ui.util.logMessage
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val sharedPreferences: SharedPreferences,
    private val store: Store,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _configLoadStatus: MutableLiveData<LoadConfigsState> = MutableLiveData()
    val configLoadStatus: LiveData<LoadConfigsState>
        get() = _configLoadStatus

    fun loadLocalSettings() {
        store.adult = sharedPreferences.getBoolean(SETTING_ADULT, false)
    }

    fun loadApiConfigs(stage: LoadError? = null) {
        if (stage == null) {
            loadApiSetting()
        } else {
            when (stage) {
                LoadError.ApiError -> {
                    loadApiSetting()
                }
                LoadError.CountriesError -> {
                    loadCountriesSetting()
                }
                LoadError.JobsError -> {
                    loadJobsSetting()
                }
                LoadError.LanguagesError -> {
                    loadLanguagesSetting()
                }
                LoadError.TimeZonesError -> {
                    loadTimeZonesSetting()
                }
            }
        }
    }

    private fun loadApiSetting() {
        viewModelScope.launch(dispatchers.io) {
            val apiConfig = settingsRepository.getApiConfig()
            if (apiConfig == null) {
                _configLoadStatus.postValue(LoadError.ApiError)
            } else {
                _configLoadStatus.postValue(LoadConfigsState.Api)
                // TODO ("сохранение требуемых данных конфигурации в Store")
                loadCountriesSetting()
            }
        }
    }

    private fun loadCountriesSetting() {
        viewModelScope.launch(dispatchers.io) {
            val countriesConfig = settingsRepository.getCountriesConfig()
            if (countriesConfig == null) {
                _configLoadStatus.postValue(LoadError.CountriesError)
            } else {
                _configLoadStatus.postValue(LoadConfigsState.Counties)
                store.countries = countriesConfig
                logMessage(store.countries.toString())
                loadJobsSetting()
            }
        }
    }

    private fun loadJobsSetting() {
        viewModelScope.launch(dispatchers.io) {
            val jobsConfig = settingsRepository.getJobsConfig()
            if (jobsConfig == null) {
                _configLoadStatus.postValue(LoadError.JobsError)
            } else {
                _configLoadStatus.postValue(LoadConfigsState.Jobs)
                // TODO ("сохранение требуемых данных по Jobs в Store")
                loadLanguagesSetting()
            }
        }
    }

    private fun loadLanguagesSetting() {
        viewModelScope.launch(dispatchers.io) {
            val languagesConfig = settingsRepository.getLanguagesConfig()
            if (languagesConfig == null) {
                _configLoadStatus.postValue(LoadError.LanguagesError)
            } else {
                _configLoadStatus.postValue(LoadConfigsState.Counties)
                store.languages = languagesConfig
                logMessage(store.languages.toString())
                loadTimeZonesSetting()
            }
        }
    }

    private fun loadTimeZonesSetting() {
        viewModelScope.launch(dispatchers.io) {
            val timeZonesConfig = settingsRepository.getTimeZonesConfig()
            if (timeZonesConfig == null) {
                _configLoadStatus.postValue(LoadError.TimeZonesError)
            } else {
                _configLoadStatus.postValue(LoadConfigsState.LoadingSuccessful)
                store.timeZones = timeZonesConfig
                logMessage(store.timeZones.toString())
            }
        }
    }
}
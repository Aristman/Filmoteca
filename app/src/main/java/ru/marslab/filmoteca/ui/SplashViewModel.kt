package ru.marslab.filmoteca.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.domain.repository.DatabaseRepository
import ru.marslab.filmoteca.domain.repository.SettingsRepository
import ru.marslab.filmoteca.ui.model.LoadConfigsState
import ru.marslab.filmoteca.ui.model.LoadConfigsState.LoadError
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val databaseRepository: DatabaseRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _configLoadStatus: MutableLiveData<LoadConfigsState> = MutableLiveData()
    val configLoadStatus: LiveData<LoadConfigsState>
        get() = _configLoadStatus

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
                databaseRepository.saveCountries(countriesConfig)
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
                databaseRepository.saveLanguages(languagesConfig)
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
                databaseRepository.saveTimeZones(timeZonesConfig)
            }
        }
    }
}
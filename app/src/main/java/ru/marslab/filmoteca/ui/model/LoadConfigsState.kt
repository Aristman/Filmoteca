package ru.marslab.filmoteca.ui.model

sealed class LoadConfigsState {
    sealed class LoadError: LoadConfigsState() {
        object ApiError: LoadError()
        object CountriesError: LoadError()
        object JobsError: LoadError()
        object LanguagesError: LoadError()
        object TimeZonesError: LoadError()
    }
    object Api: LoadConfigsState()
    object Counties: LoadConfigsState()
    object Jobs: LoadConfigsState()
    object Languages: LoadConfigsState()
    object TimeZones: LoadConfigsState()
    object LoadingSuccessful: LoadConfigsState()
}

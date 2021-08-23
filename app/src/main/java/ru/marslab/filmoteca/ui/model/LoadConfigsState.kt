package ru.marslab.filmoteca.ui.model

sealed class LoadConfigsState {
    data class LoadError(val stage: ErrorStage): LoadConfigsState()
    object Api: LoadConfigsState()
    object Counties: LoadConfigsState()
    object Jobs: LoadConfigsState()
    object Languages: LoadConfigsState()
    object TimeZones: LoadConfigsState()
    object LoadingSuccessful: LoadConfigsState()

    enum class ErrorStage {
        API_ERROR,
        COUNTRIES_ERROR,
        JOBS_ERROR,
        LANGUAGES_ERROR,
        TIME_ZONES_ERROR
    }
}

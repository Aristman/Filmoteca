package ru.marslab.filmoteca.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.Storage
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storage: Storage
) : ViewModel() {

    var adult: Boolean
        get() = storage.adult
        set(value) {
            storage.saveSettingAdult(value)
        }
}
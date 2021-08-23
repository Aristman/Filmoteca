package ru.marslab.filmoteca.ui.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.domain.repository.Store
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val store: Store
) : ViewModel() {

    var adult: Boolean
        get() = store.adult
        set(value) {
            store.adult = value
            sharedPreferences.edit()
                .putBoolean(Constants.SETTING_ADULT, value)
                .apply()
        }

}
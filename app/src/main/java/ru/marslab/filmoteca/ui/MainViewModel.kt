package ru.marslab.filmoteca.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.AppDispatchers
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.domain.Store
import javax.inject.Inject

private const val LOADING_DELAY = 500L

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dispatchers: AppDispatchers,
    private val context: Context
) : ViewModel() {

    var settingAdult: Boolean
        get() {
            return sharedPreferences.getBoolean(context.getString(R.string.setting_tag_adult), false)
        }
        set(value) {
            sharedPreferences.edit()
                .putBoolean(context.getString(R.string.setting_tag_adult), value)
                .apply()
        }

}
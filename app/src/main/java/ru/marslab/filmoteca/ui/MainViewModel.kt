package ru.marslab.filmoteca.ui

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.Store
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val store: Store
) : ViewModel() {
    fun loadSettings() {
        store.adult = sharedPreferences.getBoolean(Store.SETTING_ADULT, false)
    }
}
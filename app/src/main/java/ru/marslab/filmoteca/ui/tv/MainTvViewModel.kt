package ru.marslab.filmoteca.ui.tv

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainTvViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}
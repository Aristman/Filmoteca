package ru.marslab.filmoteca.ui.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Inject

@HiltViewModel
class MainFilmViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}
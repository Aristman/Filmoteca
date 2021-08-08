package ru.marslab.filmoteca.ui.film

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Inject

@HiltViewModel
class MainFilmViewModel @Inject constructor(repository: Repository) : ViewModel(){
}
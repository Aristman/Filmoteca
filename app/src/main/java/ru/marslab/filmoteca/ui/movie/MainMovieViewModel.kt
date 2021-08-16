package ru.marslab.filmoteca.ui.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.model.Movie
import ru.marslab.filmoteca.domain.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MainMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
}
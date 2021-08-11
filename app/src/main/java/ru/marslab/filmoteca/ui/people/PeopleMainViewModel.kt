package ru.marslab.filmoteca.ui.people

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Inject

@HiltViewModel
class PeopleMainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
}
package ru.marslab.filmoteca.ui.guest

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Inject

@HiltViewModel
class GuestViewModel @Inject constructor(repository: Repository): ViewModel() {
}
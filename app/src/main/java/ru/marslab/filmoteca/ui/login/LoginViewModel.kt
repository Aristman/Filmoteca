package ru.marslab.filmoteca.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.marslab.filmoteca.domain.Repository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(repository: Repository): ViewModel() {
}
package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.databinding.FragmentMainFilmBinding

@AndroidEntryPoint
class MainFilmFragment : Fragment() {
    private var _binding: FragmentMainFilmBinding? = null
    private val binding: FragmentMainFilmBinding
        get() = _binding!!
    private val mainFilmViewModel by viewModels<MainFilmViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFilmBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
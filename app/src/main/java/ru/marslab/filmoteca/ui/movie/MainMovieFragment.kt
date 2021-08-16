package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.databinding.FragmentMainMovieBinding

@AndroidEntryPoint
class MainMovieFragment : Fragment() {
    private var _binding: FragmentMainMovieBinding? = null
    private val binding: FragmentMainMovieBinding
        get() = _binding!!
    private val mainFilmViewModel by viewModels<MainMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMovieBinding.inflate(inflater, container, false)
        requireActivity()
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
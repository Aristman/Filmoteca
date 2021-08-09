package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMovieDelailBinding

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDelailBinding? = null
    private val binding: FragmentMovieDelailBinding
        get() = _binding!!
    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDelailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        movieDetailViewModel.getMovieDetailInfo(args.movieId)
    }

    private fun initObservers() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) {movie ->
            binding.apply {
                movieTitle.text = movie.title
                movieOriginTitle.text = movie.titleOrigin
                movie.poster?.let {
                    //TODO ("Загрузка постера фильма")
                }
                movieGanre.text = movie.ganre.joinToString(separator = ",")
                movieRelease.text = movie.release
                movieTiming.text = movie.timing.toString()
                movieDescription.text = movie.description
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
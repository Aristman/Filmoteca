package ru.marslab.filmoteca.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.databinding.FragmentMovieDelailBinding
import ru.marslab.filmoteca.domain.repository.Store
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.movie.MovieDetailFragmentArgs
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessage

@AndroidEntryPoint
class TvDetailFragment : Fragment() {
    private var _binding: FragmentMovieDelailBinding? = null
    private val binding: FragmentMovieDelailBinding
        get() = _binding!!
    private val tvDetailViewModel by viewModels<TvDetailViewModel>()
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
        tvDetailViewModel.getTvDetailInfo(args.movieId)
    }

    private fun initObservers() {
        tvDetailViewModel.tvDetail.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.LoadError -> {
                    requireView().showMessage(viewState.message)
                }
                ViewState.Loading -> {
                }
                is ViewState.Successful<*> -> {
                    val data = viewState.data as MovieDetailUi
                    binding.apply {
                        movieTitle.text = data.title
                        movieOriginTitle.text = data.titleOrigin
                        data.poster?.let {
                            moviePoster.load(it)
                        }
                        movieGanre.text = data.genres.joinToString(separator = ",")
                        movieRelease.text = data.release
                        movieTiming.text = data.timing.toString()
                        movieDescription.text = data.description
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
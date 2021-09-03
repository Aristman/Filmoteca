package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMovieDelailBinding
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessage
import ru.marslab.filmoteca.ui.util.toTimeString
import java.text.DecimalFormat
import java.util.*


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDelailBinding? = null
    private val binding: FragmentMovieDelailBinding
        get() = _binding!!
    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    private val args: MovieDetailFragmentArgs by navArgs()
    private var isFavorite: Boolean = false

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
        initListeners()
        movieDetailViewModel.getMovieDetailInfo(args.movieId)
    }

    private fun initListeners() {
        binding.favoriteImage.setOnClickListener {
            if (isFavorite) {
                binding.favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                binding.favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_like_24)
            }
            isFavorite = !isFavorite

        }
    }

    private fun initObservers() {
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.LoadError -> {
                    requireView().showMessage(viewState.message)
                }
                ViewState.Loading -> {
                }
                is ViewState.Successful<*> -> {
                    val data = viewState.data as MovieDetailUi
                    updateUi(data)
                }
            }
        }
        movieDetailViewModel.movieComment.observe(viewLifecycleOwner) { comment ->
            binding.movieComment.setText(comment)
        }
    }

    private fun updateUi(data: MovieDetailUi) {
        with(binding) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = data.title
            movieOriginTitle.text = data.originalTitle
            data.poster?.let {
                moviePoster.load(it) {
                    transformations(RoundedCornersTransformation(Constants.IMAGE_CORNER_RADIUS))
                }
            }
            data.backDrop?.let {
                backdropImage.load(it)
            }
            movieGenres.text = data.genres
            movieRelease.text = data.release
            data.timing?.let {
                movieTiming.text = it.toTimeString(getString(R.string.time_string_pattern))
            }
            movieRating.text = DecimalFormat(Constants.RATED_STRING_FORMAT).format(data.userRating)
            movieDescription.text = data.description
        }
    }

    override fun onPause() {
        movieDetailViewModel.saveMovieDataToHistory(
            args.movieId,
            Date().time,
            binding.movieComment.text ?: ""
        )
        super.onPause()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
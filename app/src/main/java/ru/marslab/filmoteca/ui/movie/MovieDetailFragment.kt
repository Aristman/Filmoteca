package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMovieDelailBinding
import ru.marslab.filmoteca.domain.model.People
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.ui.mapper.toShortUi
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.movie.adapter.PeopleListAdapter
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
    private val actorListAdapter: PeopleListAdapter by lazy {
        PeopleListAdapter { handleClickToActor(it.id) }
    }
    private val employeeListAdapter: PeopleListAdapter by lazy {
        PeopleListAdapter { handleClickToEmployee(it.id) }
    }
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
        initRv()
        movieDetailViewModel.getMovieDetailInfo(args.movieId)
        movieDetailViewModel.getMoviePeople(args.movieId)
    }

    private fun initRv() {
        binding.run {
            rvListActors.adapter = actorListAdapter
            rvListEmployees.adapter = employeeListAdapter
        }
    }

    private fun handleClickToEmployee(employeeId: Int) {
        requireView().showMessage("Employee - $employeeId")
        //TODO("Not yet implemented")
    }

    private fun handleClickToActor(actorId: Int) {
        requireView().showMessage("actor - $actorId")
        // TODO("Not yet implemented")
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
                    viewState.error.message?.let { requireView().showMessage(it) }
                }
                ViewState.Loading -> {
                }
                is ViewState.Successful<*> -> {
                    val data = viewState.data as MovieDetailUi
                    updateUi(data)
                }
                ViewState.Init -> {
                }
            }
        }
        movieDetailViewModel.movieComment.observe(viewLifecycleOwner) { comment ->
            binding.movieComment.setText(comment)
        }
        lifecycleScope.launch {
            movieDetailViewModel.moviePeople.collect { viewState ->
                when (viewState) {
                    ViewState.Init -> {
                    }
                    ViewState.Loading -> {
                        updateUiWhenLoadingPeople()
                    }
                    is ViewState.LoadError -> {
                        handleErrorLoadPeople(viewState.error.message)
                    }
                    is ViewState.Successful<*> -> {
                        val people = viewState.data as? People
                        people?.actors?.let { actors ->
                            actorListAdapter.submitList(actors.map { it.toShortUi() })
                        }
                        people?.employees?.let { employees ->
                            employeeListAdapter.submitList(employees.map { it.toShortUi() })
                        }
                    }
                }
            }
        }
    }

    private fun handleErrorLoadPeople(message: String?) {
        message?.let { requireView().showMessage(it) }
    }

    private fun updateUiWhenLoadingPeople() {
        binding.rvListEmployees.visibility = View.GONE
        binding.rvListActors.visibility = View.GONE
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
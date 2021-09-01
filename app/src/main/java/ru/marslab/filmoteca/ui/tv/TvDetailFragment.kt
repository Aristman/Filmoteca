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
import ru.marslab.filmoteca.databinding.FragmentTvDelailBinding
import ru.marslab.filmoteca.ui.model.MovieDetailUi
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessage

@AndroidEntryPoint
class TvDetailFragment : Fragment() {
    private var _binding: FragmentTvDelailBinding? = null
    private val binding: FragmentTvDelailBinding
        get() = _binding!!
    private val tvDetailViewModel by viewModels<TvDetailViewModel>()
    private val args: TvDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvDelailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        tvDetailViewModel.getTvDetailInfo(args.tvShowId)
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
                        movieOriginTitle.text = data.originalTitle
                        data.poster?.let {
                            moviePoster.load(it)
                        }
                        movieGenres.text = data.genres.joinToString(separator = ",")
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
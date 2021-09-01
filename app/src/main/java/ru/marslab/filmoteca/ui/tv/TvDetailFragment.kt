package ru.marslab.filmoteca.ui.tv

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
import ru.marslab.filmoteca.databinding.FragmentTvDelailBinding
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.ui.model.TvShowDetailUi
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessage

@AndroidEntryPoint
class TvDetailFragment : Fragment() {
    private var _binding: FragmentTvDelailBinding? = null
    private val binding: FragmentTvDelailBinding
        get() = _binding!!
    private val tvDetailViewModel by viewModels<TvDetailViewModel>()
    private val args: TvDetailFragmentArgs by navArgs()
    private var isFavorite: Boolean = false

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
        initListeners()
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
                    val data = viewState.data as TvShowDetailUi
                    binding.apply {
                        (activity as AppCompatActivity).supportActionBar?.title = data.title
                        tvOriginTitle.text = data.originalTitle
                        data.poster?.let {
                            tvPoster.load(it) {
                                transformations(RoundedCornersTransformation(Constants.IMAGE_CORNER_RADIUS))
                            }
                        }
                        data.backDrop?.let {
                            backdropImage.load(it)
                        }
                        tvGenres.text = data.genreString
                        tvRating.text = data.userRating.toString()
                        tvRelease.text = data.firstAirDate
                        tvCountSeasons.text =
                            getString(R.string.count_seasons_text, data.numberOfSeasons)
                        tvCountEpisodes.text =
                            getString(R.string.count_episodes_text, data.numberOfEpisodes)
                        tvDescription.text = data.description
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.run {
            favoriteImage.setOnClickListener {
                if (isFavorite) {
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                } else {
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_like_24)
                }
                isFavorite = isFavorite.not()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
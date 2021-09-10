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
import java.text.DecimalFormat

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
                    viewState.error.message?.let { requireView().showMessage(it) }
                }
                ViewState.Loading -> {
                }
                is ViewState.Successful<*> -> {
                    val data = viewState.data as TvShowDetailUi
                    updateUi(data)
                }
            }
        }
    }

    private fun updateUi(data: TvShowDetailUi) {
        with(binding) {
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
            tvRating.text = DecimalFormat(Constants.RATED_STRING_FORMAT).format(data.userRating)
            tvRelease.text = data.firstAirDate
            tvCountSeasons.text =
                getString(R.string.count_seasons_text, data.numberOfSeasons)
            tvCountEpisodes.text =
                getString(R.string.count_episodes_text, data.numberOfEpisodes)
            tvDescription.text = data.description
        }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
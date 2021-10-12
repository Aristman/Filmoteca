package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMovieMainBinding
import ru.marslab.filmoteca.databinding.RvLayoutHorizonShortListBinding
import ru.marslab.filmoteca.ui.mapper.toUiShort
import ru.marslab.filmoteca.ui.movie.adapter.MovieHorizonPagingAdapter
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow


@AndroidEntryPoint
class MovieMainFragment : Fragment() {

    private var _binding: FragmentMovieMainBinding? = null
    private val binding: FragmentMovieMainBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }

    private val movieMainViewModel by viewModels<WelcomeViewModel>()

    private val popularMoviesPagingAdapter: MovieHorizonPagingAdapter by lazy {
        MovieHorizonPagingAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private val topRatedMoviesPagingAdapter: MovieHorizonPagingAdapter by lazy {
        MovieHorizonPagingAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private fun showMovieDetailsFragment(id: Int) {
        val action = MovieMainFragmentDirections.actionMovieMainFragmentToMovieDetailFragment(id)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initRv()
    }

    private fun initObservers() {
        movieMainViewModel.run {
            lifecycleScope.launchWhenStarted {
                popularMovies.collectLatest { pagingData ->
                    popularMoviesPagingAdapter.submitData(pagingData.map { it.toUiShort() })
                }
            }
            lifecycleScope.launchWhenStarted {
                ratedMovies.collectLatest { pagingData ->
                    topRatedMoviesPagingAdapter.submitData(pagingData.map { it.toUiShort() })
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            popularMovies.listTitle.text = getString(R.string.popular_movies_title)
            topRatedMovies.listTitle.text = getString(R.string.top_rated_movies_title)
            showDataLayout(popularMovies)
            showDataLayout(topRatedMovies)
        }
    }

    private fun initRv() {
        binding.run {
            setupRecyclerView(this.popularMovies, popularMoviesPagingAdapter)
            setupRecyclerView(this.topRatedMovies, topRatedMoviesPagingAdapter)
        }
    }

    private fun showDataLayout(itemBinding: RvLayoutHorizonShortListBinding) {
        with(itemBinding) {
            dataLayout.viewShow()
            loadingIndicator.viewHide()
        }
    }

    private fun setupRecyclerView(
        itemBinding: RvLayoutHorizonShortListBinding,
        itemAdapter: MovieHorizonPagingAdapter
    ) {
        itemBinding.run {
            with(listRv) {
                adapter = itemAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
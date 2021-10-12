package ru.marslab.filmoteca.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMovieMainBinding
import ru.marslab.filmoteca.databinding.RvLayoutHorizonShortListBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.movie.adapter.HorizonListAdapter
import ru.marslab.filmoteca.ui.movie.adapter.MovieHorizonPagingAdapter
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessageWithAction
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow

@AndroidEntryPoint
class MovieMainFragment : Fragment() {

    private var _binding: FragmentMovieMainBinding? = null
    private val binding: FragmentMovieMainBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }

    private val movieMainViewModel by viewModels<WelcomeViewModel>()

    private val popularMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private val popularMoviesPagingAdapter: MovieHorizonPagingAdapter by lazy {
        MovieHorizonPagingAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private val topRatedMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
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

    @Suppress("UNCHECKED_CAST")
    private fun initObservers() {
        movieMainViewModel.run {
            lifecycleScope.launchWhenStarted {
                popularMoviesPagingAdapter.submitData(PagingData.empty())
                popularMoviesFlow.collectLatest { pagingData ->
                    Log.d("MY_TAG", pagingData.toString())
//                    popularMoviesPagingAdapter.submitData(pagingData.map { it.toUiShort() })
                }
            }
            popularMovies.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_movies_error,
                            R.string.repeat
                        ) {
                            movieMainViewModel.loadPopularMovies()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.popularMovies)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as? List<MovieShortUi>
                        showDataLayout(binding.popularMovies)
                        popularMoviesAdapter.submitList(data)
                    }
                    ViewState.Init -> {
                        movieMainViewModel.loadPopularMovies()
                    }
                }
            }
            topRatedMovies.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_movies_error,
                            R.string.repeat
                        ) {
                            movieMainViewModel.loadTopRatedMovies()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.topRatedMovies)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as? List<MovieShortUi>
                        showDataLayout(binding.topRatedMovies)
                        topRatedMoviesAdapter.submitList(data)
                    }
                    ViewState.Init -> {
                        movieMainViewModel.loadTopRatedMovies()
                    }
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
            setupPagingRecyclerView(this.popularMovies, popularMoviesPagingAdapter)
//            setupRecyclerView(this.popularMovies, popularMoviesAdapter)
            setupRecyclerView(this.topRatedMovies, topRatedMoviesAdapter)
        }
    }

    private fun setupPagingRecyclerView(
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

    private fun showDataLayout(itemBinding: RvLayoutHorizonShortListBinding) {
        with(itemBinding) {
            dataLayout.viewShow()
            loadingIndicator.viewHide()
        }
    }

    private fun showLoading(itemBinding: RvLayoutHorizonShortListBinding) {
        with(itemBinding) {
            dataLayout.viewHide()
            loadingIndicator.viewShow()
        }
    }

    private fun setupRecyclerView(
        itemBinding: RvLayoutHorizonShortListBinding,
        itemAdapter: HorizonListAdapter
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
package ru.marslab.filmoteca.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentWelcomeScreenBinding
import ru.marslab.filmoteca.databinding.RvLayoutHorizonShortListBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.util.*
import ru.marslab.filmoteca.ui.welcome.adapter.HorizonListAdapter

@AndroidEntryPoint
class WelcomeScreenFragment : Fragment() {

    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding: FragmentWelcomeScreenBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }

    private val welcomeViewModel by viewModels<WelcomeViewModel>()

    private val popularMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private val popularTvShowsAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            showMovieDetailsFragment(it.id)
        }
    }

    private val topRatedMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            showMovieDetailsFragment(it.id)
        }
    }
    private fun showMovieDetailsFragment(id: Int) {
        val action = WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToMovieDetailFragment(id)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initRv()
    }

    private fun initObservers() {
        welcomeViewModel.run {
            popularMovies.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_movies_error,
                            R.string.repeat
                        ) {
                            welcomeViewModel.loadPopularMovies()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.popularMovies)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as List<MovieShortUi>
                        showDataLayout(binding.popularMovies)
                        popularMoviesAdapter.submitList(data)
                    }
                }
            }
            popularTvShows.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_tvshows_error,
                            R.string.repeat
                        ) {
                            welcomeViewModel.loadPopularTvShows()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.popularTvShows)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as List<MovieShortUi>
                        showDataLayout(binding.popularTvShows)
                        popularTvShowsAdapter.submitList(data)
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
                            welcomeViewModel.loadTopRatedMovies()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.topRatedMovies)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as List<MovieShortUi>
                        showDataLayout(binding.topRatedMovies)
                        topRatedMoviesAdapter.submitList(data)
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            popularMovies.listTitle.text = getString(R.string.popular_movies_title)
            popularTvShows.listTitle.text = getString(R.string.popular_tvshows_title)
            topRatedMovies.listTitle.text = getString(R.string.top_rated_movies_title)
            showDataLayout(popularMovies)
            showDataLayout(popularTvShows)
            showDataLayout(topRatedMovies)
        }
    }

    private fun initRv() {
        binding.run {
            setupPopularMovies(this.popularMovies, popularMoviesAdapter)
            setupPopularMovies(this.popularTvShows, popularTvShowsAdapter)
            setupPopularMovies(this.topRatedMovies, topRatedMoviesAdapter)
            welcomeViewModel.loadPopularMovies()
            welcomeViewModel.loadPopularTvShows()
            welcomeViewModel.loadTopRatedMovies()
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

    private fun setupPopularMovies(
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
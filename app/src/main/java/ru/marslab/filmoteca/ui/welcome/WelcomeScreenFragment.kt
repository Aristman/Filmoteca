package ru.marslab.filmoteca.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentWelcomeScreenBinding
import ru.marslab.filmoteca.databinding.RvLayoutHorizonShortListBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.util.*
import ru.marslab.filmoteca.ui.welcome.adapter.HorizonListAdapter

class WelcomeScreenFragment : Fragment() {

    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding: FragmentWelcomeScreenBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }

    private val welcomeViewModel by viewModels<WelcomeViewModel>()

    private val popularMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            // TODO("Обработка нажатия на список популярных фильмов")
        }
    }
    private val popularTvShowsAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            // TODO("Обработка нажатия на список популярных тв шоу")
        }
    }
    private val recommendationMoviesAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            // TODO("Обработка нажатия на список рекомендуемых фильмов")
        }
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
            recommendationMovies.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_movies_error,
                            R.string.repeat
                        ) {
                            welcomeViewModel.loadRecommendationMovies()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.recommendationMovies)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as List<MovieShortUi>
                        showDataLayout(binding.recommendationMovies)
                        recommendationMoviesAdapter.submitList(data)
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            showDataLayout(popularMovies)
            showDataLayout(popularTvShows)
            showDataLayout(recommendationMovies)
        }
    }

    private fun initRv() {
        binding.run {
            setupPopularMovies(this.popularMovies, popularMoviesAdapter)
            setupPopularMovies(this.popularTvShows, popularTvShowsAdapter)
            setupPopularMovies(this.recommendationMovies, recommendationMoviesAdapter)
            welcomeViewModel.loadPopularMovies()
            welcomeViewModel.loadPopularTvShows()
            welcomeViewModel.loadRecommendationMovies()
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
package ru.marslab.filmoteca.ui.tv

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
import ru.marslab.filmoteca.databinding.FragmentTvMainBinding
import ru.marslab.filmoteca.databinding.RvLayoutHorizonShortListBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.movie.adapter.HorizonListAdapter
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessageWithAction
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow

@AndroidEntryPoint
class TvMainFragment : Fragment() {
    private var _binding: FragmentTvMainBinding? = null
    private val binding: FragmentTvMainBinding
        get() = _binding!!
    private val mainTvViewModel by viewModels<TvMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    private val popularTvShowsAdapter: HorizonListAdapter by lazy {
        HorizonListAdapter {
            showTvDetailsFragment(it.id)
        }
    }

    private fun showTvDetailsFragment(id: Int) {
        val action = TvMainFragmentDirections.actionMainTvFragmentToTvDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initRv()
    }

    private fun initRv() {
        binding.run {
            setupRecyclerView(popularTvShows, popularTvShowsAdapter)
            mainTvViewModel.loadPopularTvShows()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun initObservers() {
        mainTvViewModel.run {
            popularTvShows.observe(viewLifecycleOwner) { viewState ->
                when (viewState) {
                    is ViewState.LoadError -> {
                        requireView().showMessageWithAction(
                            R.string.load_list_tvshows_error,
                            R.string.repeat
                        ) {
                            mainTvViewModel.loadPopularTvShows()
                        }
                    }
                    ViewState.Loading -> {
                        showLoading(binding.popularTvShows)
                    }
                    is ViewState.Successful<*> -> {
                        val data = viewState.data as? List<MovieShortUi>
                        showDataLayout(binding.popularTvShows)
                        popularTvShowsAdapter.submitList(data)
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            popularTvShows.listTitle.text = getString(R.string.popular_tvshows_title)
            showDataLayout(popularTvShows)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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
}
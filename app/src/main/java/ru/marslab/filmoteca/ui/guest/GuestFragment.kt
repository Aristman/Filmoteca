package ru.marslab.filmoteca.ui.guest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentGuestBinding
import ru.marslab.filmoteca.ui.guest.adapter.RatedMoviesAdapter
import ru.marslab.filmoteca.ui.model.RatedMoviesUi
import ru.marslab.filmoteca.ui.util.ViewState

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class GuestFragment : Fragment() {
    private var _binding: FragmentGuestBinding? = null
    private val binding: FragmentGuestBinding
        get() = _binding!!
    private val guestViewModel by viewModels<GuestViewModel>()
    private lateinit var ratedMovesAdapter: RatedMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuestBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRv()
    }

    private fun initObservers() {
        guestViewModel.ratedMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ViewState.Successful<*> -> {
                    showDataLayout()
                    val data = result.data as? List<RatedMoviesUi>
                    data?.let {  ratedMovesAdapter.submitList(it) }
                }
                is ViewState.Loading -> {
                    showLoadingLayout()
                }
                is ViewState.LoadError -> {
                    showRepeatMessage(result.message)
                }
            }
        }
    }

    private fun showLoadingLayout() {
        with(binding) {
            dataLayout.visibility = View.GONE
            loadingIndicator.visibility = View.VISIBLE
        }
    }

    private fun showDataLayout() {
        with(binding) {
            dataLayout.visibility = View.VISIBLE
            loadingIndicator.visibility = View.GONE
        }
    }

    private fun showRepeatMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.repeat)) {
                guestViewModel.getRatedMoviesList()
            }
            .show()
    }

    private fun initRv() {
        ratedMovesAdapter = RatedMoviesAdapter { movie ->
            showMovieDetail(movie.id)
        }
        with(binding.ratedMovies.rvRatedMoves) {
            adapter = ratedMovesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        guestViewModel.getRatedMoviesList()
    }

    private fun showMovieDetail(id: Int) {
        val action = GuestFragmentDirections.actionGuestFragmentToMovieDetailFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
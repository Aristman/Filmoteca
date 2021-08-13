package ru.marslab.filmoteca.ui.guest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentGuestBinding
import ru.marslab.filmoteca.ui.guest.adapter.RatedMoviesAdapter
import ru.marslab.filmoteca.ui.model.MovieShortUi
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.showMessageWithAction
import ru.marslab.filmoteca.ui.util.viewShow

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
            ratedMovesAdapter.submitList(result)
        }
    }

    private fun initRv() {
        ratedMovesAdapter = RatedMoviesAdapter { movie ->
            showMovieDetail(movie.id)
        }
        binding.ratedMovies.rvRatedMoves.apply {
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
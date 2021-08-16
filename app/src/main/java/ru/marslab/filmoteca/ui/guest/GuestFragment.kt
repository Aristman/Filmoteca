package ru.marslab.filmoteca.ui.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.databinding.FragmentGuestBinding
import ru.marslab.filmoteca.ui.guest.adapter.RatedMoviesAdapter

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
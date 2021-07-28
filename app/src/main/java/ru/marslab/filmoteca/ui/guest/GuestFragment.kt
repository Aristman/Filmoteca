package ru.marslab.filmoteca.ui.guest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentGuestBinding

class GuestFragment : Fragment() {
    private var _binding: FragmentGuestBinding? = null
    private val binding: FragmentGuestBinding
        get() = _binding!!
    private val guestViewModel by viewModels<GuestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuestBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
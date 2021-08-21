package ru.marslab.filmoteca.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.databinding.FragmentTvMainBinding

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
package ru.marslab.filmoteca.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private var _binding: FragmentHelpBinding? = null
    private val binding: FragmentHelpBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
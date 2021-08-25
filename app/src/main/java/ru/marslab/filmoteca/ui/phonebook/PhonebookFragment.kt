package ru.marslab.filmoteca.ui.phonebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentPhonebookBinding

class PhonebookFragment : Fragment() {

    private var _binding: FragmentPhonebookBinding? = null
    private val binding: FragmentPhonebookBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhonebookBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
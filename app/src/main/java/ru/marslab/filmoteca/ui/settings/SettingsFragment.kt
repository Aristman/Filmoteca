package ru.marslab.filmoteca.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentSettingsBinding

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val adapterSpinner: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
    }
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        settingsViewModel.languagesList.observeForever {
            adapterSpinner.clear()
            adapterSpinner.addAll(it)
            adapterSpinner.notifyDataSetChanged()
            binding.settingLanguage.setSelection(
                adapterSpinner.getPosition(settingsViewModel.language)
            )
        }
    }

    private fun initListeners() {
        binding.run {
            settingAdult.setOnCheckedChangeListener { _, isChecked ->
                settingsViewModel.adult = isChecked
            }
            settingLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        settingsViewModel.language = adapterSpinner.getItem(position).toString()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
    }

    private fun initView() {
        settingsViewModel.getLanguagesList()
        binding.run {
            settingAdult.isChecked = settingsViewModel.adult
            settingLanguage.adapter = adapterSpinner
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
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
import ru.marslab.filmoteca.domain.model.Language
import ru.marslab.filmoteca.domain.model.TimeZone

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val adapterLanguages: ArrayAdapter<Language> by lazy {
        ArrayAdapter<Language>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
    }
    private val adapterTimeZones: ArrayAdapter<TimeZone> by lazy {
        ArrayAdapter<TimeZone>(requireContext(), android.R.layout.simple_spinner_dropdown_item)
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
        settingsViewModel.languagesList.observeForever { languages ->
            adapterLanguages.clear()
            adapterLanguages.addAll(languages)
            adapterLanguages.notifyDataSetChanged()
            binding.settingLanguage.setSelection(
                adapterLanguages.getPosition(settingsViewModel.getStorageLanguage())
            )
        }
        settingsViewModel.timeZonesList.observeForever { timeZones ->
            adapterTimeZones.clear()
            adapterTimeZones.addAll(timeZones)
            adapterTimeZones.notifyDataSetChanged()
            binding.settingTimeZone.setSelection(
                adapterTimeZones.getPosition(settingsViewModel.getStorageTimeZone())
            )
        }
        settingsViewModel.adult.observeForever {
            binding.settingAdult.isChecked = it
        }
    }

    private fun initListeners() {
        binding.run {
            settingAdult.setOnCheckedChangeListener { _, isChecked ->
                settingsViewModel.setAdult(isChecked)
            }
            settingLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        adapterLanguages.getItem(position)
                            ?.let { settingsViewModel.setLanguage(it) }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            settingTimeZone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        adapterTimeZones.getItem(position)
                            ?.let { settingsViewModel.setTimeZone(it) }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        }
    }

    private fun initView() {
        settingsViewModel.getLanguagesList()
        settingsViewModel.getTimeZonesList()
        binding.run {
            settingAdult.isChecked = settingsViewModel.getAdultStorageValue()
            settingLanguage.adapter = adapterLanguages
            settingTimeZone.adapter = adapterTimeZones
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
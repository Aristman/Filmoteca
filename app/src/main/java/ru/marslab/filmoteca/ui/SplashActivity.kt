package ru.marslab.filmoteca.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.ActivitySplashBinding
import ru.marslab.filmoteca.domain.repository.Store.Companion.START_DELAY
import ru.marslab.filmoteca.ui.model.LoadConfigsState
import ru.marslab.filmoteca.ui.util.showMessageWithAction

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainViewModel.loadLocalSettings()
        mainViewModel.loadApiConfigs()
        initObservers()
    }

    private fun delayLayoutClose() {
        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            delay(START_DELAY)
            startActivity(intent)
            this@SplashActivity.finish()
        }
    }

    private fun initObservers() {
        mainViewModel.configLoadStatus.observe(this) {loadState ->
            when (loadState) {
                LoadConfigsState.Api -> {
                    binding.loadingText.text = getString(R.string.load_api_complete)
                }
                LoadConfigsState.Counties -> {
                    binding.loadingText.text = getText(R.string.load_counries_complete)
                }
                LoadConfigsState.Jobs -> {
                    binding.loadingText.text = getString(R.string.load_jobs_complete)
                }
                LoadConfigsState.Languages -> {
                    binding.loadingText.text = getString(R.string.load_languages_complete)
                }
                LoadConfigsState.TimeZones -> {
                    binding.loadingText.text = getString(R.string.load_time_zones_complete)
                }
                LoadConfigsState.LoadingSuccessful -> {
                    binding.loadingText.text = getString(R.string.load_settings_complete)
                    delayLayoutClose()
                }
                is LoadConfigsState.LoadError -> {
                    binding.root.showMessageWithAction(
                        R.string.load_settings_error,
                        R.string.repeat
                    ) {
                        mainViewModel.loadApiConfigs(loadState)
                    }
                }
            }
        }
    }
}
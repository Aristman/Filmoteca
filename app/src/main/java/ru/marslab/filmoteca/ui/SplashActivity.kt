package ru.marslab.filmoteca.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.ActivitySplashBinding
import ru.marslab.filmoteca.domain.util.Constants.START_DELAY
import ru.marslab.filmoteca.ui.util.ViewState
import ru.marslab.filmoteca.ui.util.showMessageWithAction
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        mainViewModel.requestToken()
    }

    private fun initObservers() {
        mainViewModel.token.observe(this) { result ->
            when (result) {
                is ViewState.LoadError -> {
                    binding.loadingIndicator.viewHide()
                    binding.root.showMessageWithAction(result.message, getString(R.string.repeat)) {
                        mainViewModel.requestToken()
                    }
                }
                ViewState.Loading -> {
                    binding.loadingIndicator.viewShow()
                }
                is ViewState.Successful<*> -> {
                    binding.loadingIndicator.viewHide()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, START_DELAY)
                }
            }
        }
    }
}
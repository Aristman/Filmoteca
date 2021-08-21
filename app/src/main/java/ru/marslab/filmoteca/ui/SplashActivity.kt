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
import ru.marslab.filmoteca.databinding.ActivitySplashBinding
import ru.marslab.filmoteca.domain.repository.Store.Companion.START_DELAY

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainViewModel.loadSettings()
        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            delay(START_DELAY)
            startActivity(intent)
            this@SplashActivity.finish()
        }
    }
}
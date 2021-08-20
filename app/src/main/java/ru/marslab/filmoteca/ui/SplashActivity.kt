package ru.marslab.filmoteca.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.databinding.ActivitySplashBinding
import ru.marslab.filmoteca.domain.util.Constants.START_DELAY

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            delay(START_DELAY)
            startActivity(intent)
            this@SplashActivity.finish()
        }
    }
}
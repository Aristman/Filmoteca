package ru.marslab.filmoteca.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.data.RepositoryImpl
import ru.marslab.filmoteca.databinding.ActivityMainBinding
import ru.marslab.filmoteca.domain.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initListeners()
    }


    private fun initUi() {
    }

    private fun initListeners() {

    }

}
package ru.marslab.filmoteca.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.data.RepositoryImpl
import ru.marslab.filmoteca.domain.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var filmName: AppCompatTextView
    private lateinit var filmYear: AppCompatTextView
    private lateinit var firstButton: Button
    private val repository by lazy { RepositoryImpl }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        initListeners()
    }

    private fun initUi() {
        firstButton = findViewById(R.id.first_button)
        filmName = findViewById(R.id.film_name)
        filmYear = findViewById(R.id.film_year)
    }

    private fun initListeners() {
        firstButton.setOnClickListener {
            val randomFilm = repository.getRandomFilm()
            filmName.text = randomFilm.name
            filmYear.text = randomFilm.year.toString()
        }
    }
}
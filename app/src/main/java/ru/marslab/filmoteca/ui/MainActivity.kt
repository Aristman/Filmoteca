package ru.marslab.filmoteca.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.data.RepositoryImpl
import ru.marslab.filmoteca.domain.Repository


class MainActivity : AppCompatActivity() {
    companion object {
        const val LOG_TAG = "log_tag"
    }
    private lateinit var filmName: AppCompatTextView
    private lateinit var filmYear: AppCompatTextView
    private lateinit var firstButton: Button
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        initRepository()
        initListeners()
    }

    private fun initRepository() {
        repository = RepositoryImpl
    }

    private fun initUi() {
        firstButton = findViewById(R.id.first_button)
        filmName = findViewById(R.id.film_name)
        filmYear = findViewById(R.id.film_year)
    }

    private fun initListeners() {
        firstButton.setOnClickListener {
            val (name, year) = repository.getRandomFilm().copy()
            filmName.text = name
            filmYear.text = year.toString()
            launchCycles()
        }
    }

    private fun launchCycles() {
        val films = repository.getFilms()
        films.forEach {
            Log.d(LOG_TAG, it.name)
        }
        Log.d(LOG_TAG, "---------------------------")
        var index = 0
        while (index < films.size) {
            Log.d(LOG_TAG, films[index].name + ":" + films[index].year)
            index++
        }
        Log.d(LOG_TAG, "---------------------------")
    }
}
package ru.marslab.filmoteca.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.marslab.filmoteca.R

class MainActivity : AppCompatActivity() {
    private lateinit var firstButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    private fun initListeners() {
        firstButton = findViewById(R.id.first_button)
        firstButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.first_button), Toast.LENGTH_SHORT).show()
        }
    }
}
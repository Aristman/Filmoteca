package ru.marslab.filmoteca.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.ActivityMainBinding
import ru.marslab.filmoteca.ui.about.AboutFragment


class MainActivity : AppCompatActivity() {

    private lateinit var mainBottomNav: BottomNavigationView
    private lateinit var mainToolbar: Toolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }


    private fun initUi() {
        mainToolbar = binding.mainContent.mainToolbar
        mainBottomNav = binding.mainContent.mainBottomNav

        setSupportActionBar(mainToolbar)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainContent.mainFragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.aboutFragment -> {
                    mainToolbar.visibility = View.GONE
                    mainBottomNav.visibility = View.GONE
                }
                R.id.helpFragment -> {
                    mainToolbar.visibility = View.GONE
                    mainBottomNav.visibility = View.GONE
                }
                R.id.loginFragment -> {
                    mainToolbar.visibility = View.GONE
                    mainBottomNav.visibility = View.GONE
                }
                else -> {
                    mainToolbar.visibility = View.VISIBLE
                    mainBottomNav.visibility = View.VISIBLE
                }
            }
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        mainToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)
        mainBottomNav.setupWithNavController(navController)
    }
}
package ru.marslab.filmoteca.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }


    private fun initUi() {
        setSupportActionBar(binding.mainContent.mainToolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainContent.mainFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        binding.mainContent.mainToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)
        binding.mainContent.mainBottomNav.setupWithNavController(navController)
        initBottomNav()
    }

    private fun initBottomNav() {
        //TODO("Not yet implemented")
    }

//    override fun onSupportNavigateUp() =
//        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
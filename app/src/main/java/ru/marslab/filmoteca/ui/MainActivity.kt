package ru.marslab.filmoteca.ui

import android.content.IntentFilter
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.ActivityMainBinding
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBottomNav: BottomNavigationView
    private lateinit var mainToolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    private val broadcastReceiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(broadcastReceiver, IntentFilter(CONNECTIVITY_ACTION))
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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.aboutFragment,
                R.id.helpFragment,
                -> {
                    mainToolbar.viewHide()
                    mainBottomNav.viewHide()
                }
                R.id.loginFragment,
                R.id.mapsFragment -> {
                    mainToolbar.viewShow()
                    mainBottomNav.viewHide()
                }
                else -> {
                    mainToolbar.viewShow()
                    mainBottomNav.viewShow()
                }
            }
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        mainToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)
        mainBottomNav.setupWithNavController(navController)
    }
}
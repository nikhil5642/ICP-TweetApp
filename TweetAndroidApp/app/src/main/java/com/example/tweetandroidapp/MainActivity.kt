package com.example.tweetandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tweetandroidapp.repositories.UserRepository
import com.example.tweetandroidapp.views.login.UserViewModel
import com.example.tweetandroidapp.views.login.UserViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserViewModel

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRepositories()
        initViewModels()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up ActionBar to work with NavController (optional)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }
    private fun initRepositories(){
        userRepository = UserRepository(this)
    }
    private fun initViewModels(){
        viewModel = ViewModelProvider(this,
            UserViewModelFactory(userRepository))[UserViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
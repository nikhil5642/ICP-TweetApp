package com.example.tweetandroidapp

import android.os.Bundle
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
import com.example.tweetandroidapp.views.login.UserActionListener
import java.security.Security

class MainActivity : AppCompatActivity(),UserActionListener {
    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initRepositories()
        initViewModels()
        setUpNavigation()

        if (!userViewModel.userId.value.isNullOrEmpty()) {
           onLoginSuccess()
        }
    }
    private fun initRepositories(){
        userRepository = UserRepository(this)
    }
    private fun initViewModels(){
        userViewModel = ViewModelProvider(this,
            UserViewModelFactory(userRepository,this))[UserViewModel::class.java]
    }
    private fun setUpNavigation(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up ActionBar to work with NavController (optional)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment || destination.id ==R.id.loginFragment) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onLoginSuccess() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onLogoutSuccess() {
        navController.popBackStack(R.id.loginFragment, false) // This pops back to loginFragment without removing it
        navController.navigate(R.id.loginFragment)
    }
}
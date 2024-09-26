package com.example.tweetandroidapp.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tweetandroidapp.repositories.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository,
                           private val userActionListener: UserActionListener
    ) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository,userActionListener) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
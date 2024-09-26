package com.example.tweetandroidapp.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tweetandroidapp.repositories.TweetsRepository
import com.example.tweetandroidapp.repositories.UserRepository

class HomeViewModelFactory(private val tweetsRepository: TweetsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(tweetsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
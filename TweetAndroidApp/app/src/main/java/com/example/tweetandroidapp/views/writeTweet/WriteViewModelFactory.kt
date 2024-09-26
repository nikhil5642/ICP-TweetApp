package com.example.tweetandroidapp.views.writeTweet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tweetandroidapp.repositories.TweetsRepository
import com.example.tweetandroidapp.repositories.UserRepository

class WriteViewModelFactory( private val userRepository: UserRepository,
    private val tweetsRepository: TweetsRepository,
    private val writeTweetListener: WriteTweetListener) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WriteTweetViewModel::class.java)) {
            return WriteTweetViewModel(
                userRepository,
                tweetsRepository,
                writeTweetListener) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
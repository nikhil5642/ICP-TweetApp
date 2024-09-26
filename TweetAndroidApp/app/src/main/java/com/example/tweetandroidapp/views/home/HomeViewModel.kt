package com.example.tweetandroidapp.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tweetandroidapp.models.TweetModel

class HomeViewModel : ViewModel() {

    // LiveData to hold the list of tweets
    private val _tweets = MutableLiveData<List<TweetModel>>()
    val tweets: LiveData<List<TweetModel>>
        get() = _tweets

    init {
        loadTweets() // Load tweets when ViewModel is created
    }

    // Simulate loading tweets from repository or a backend
    private fun loadTweets() {
        // Replace this with actual repository call to load data
        _tweets.value = listOf(
            TweetModel(1, "This is my first tweet!", "User1", System.currentTimeMillis()),
            TweetModel(2, "Hello, world!", "User2", System.currentTimeMillis())
            // Add more mock tweets here
        )
    }
}

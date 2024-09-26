package com.example.tweetandroidapp.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tweetandroidapp.models.TweetModel
import com.example.tweetandroidapp.repositories.TweetsRepository

class HomeViewModel(private val tweetsRepository: TweetsRepository,
    ) : ViewModel() {

    private val _tweets = MutableLiveData<List<TweetModel>>()
    val tweets: LiveData<List<TweetModel>>
        get() = _tweets

    init {
        loadTweets()
    }

    fun loadTweets() {
        _tweets.value = tweetsRepository.getTweets().toList();
    }

}

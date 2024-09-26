package com.example.tweetandroidapp.views.writeTweet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tweetandroidapp.repositories.TweetsRepository
import com.example.tweetandroidapp.repositories.UserRepository

class WriteTweetViewModel(private val userRepository: UserRepository,
                          private val tweetsRepository: TweetsRepository,
                          private val writeTweetListener: WriteTweetListener) : ViewModel() {

    val tweetContent = MutableLiveData<String>()

    fun postTweet() {
        if (tweetContent.value?.isNotEmpty() == true) {
            tweetsRepository.postTweet(
                userRepository.getUserId(),
                tweetContent.value)
            writeTweetListener.onTweetPosted()
        }else{
            writeTweetListener.onTweetPostError()
        }
    }
}

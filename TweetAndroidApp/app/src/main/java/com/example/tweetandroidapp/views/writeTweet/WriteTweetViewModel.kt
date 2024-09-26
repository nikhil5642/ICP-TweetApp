package com.example.tweetandroidapp.views.writeTweet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tweetandroidapp.models.TweetModel
import java.util.*

class WriteTweetViewModel(private val writeTweetListener: WriteTweetListener) : ViewModel() {

    val tweetContent = MutableLiveData<String>()
    // Function to post the tweet
    fun postTweet() {
        // This is where the tweet posting logic would go (e.g., save to a repository or database)
        if (tweetContent.value?.isNotEmpty() == true) {
            val tweet = TweetModel(
                id = Random().nextLong(), // Generate a random tweet ID
                content = tweetContent.value,
                creatorId = "CurrentUser", // Replace with actual logged-in user ID
                createdAt = System.currentTimeMillis()
            )
            writeTweetListener.onTweetPosted()
        }else{
            writeTweetListener.onTweetPostError()
        }

    }
}

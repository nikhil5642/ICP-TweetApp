package com.example.tweetandroidapp.repositories

import android.content.Context
import com.example.tweetandroidapp.icp.ICClient
import com.example.tweetandroidapp.icp.TweetCanisterService
import com.example.tweetandroidapp.models.TweetModel


open class TweetsRepository(private val context: Context) {
    private val tweetService: TweetCanisterService = ICClient.getInstance(context).tweetService

    open fun  getTweets(): Array<TweetModel> {
        return tweetService.getTweets()
    }

    open fun postTweet(userId: String?, content: String?): Long {
        val createdAt = System.currentTimeMillis()
        val tweetModel = TweetModel(null, content, userId, createdAt)
        return tweetService.postTweet(tweetModel)
    }
}

package com.example.tweetandroidapp.repositories

import com.example.tweetandroidapp.icp.ICClient
import com.example.tweetandroidapp.icp.TweetCanisterService
import com.example.tweetandroidapp.models.TweetModel


class TweetsRepository(icClient: ICClient) {
    private val tweetService: TweetCanisterService = icClient.tweetService

    fun  getTweets(): Array<TweetModel> {
        return tweetService.getTweets()
    }

    fun postTweet(userId: String?, content: String?): Long {
        val createdAt = System.currentTimeMillis()
        val tweetModel = TweetModel(null, content, userId, createdAt)
        return tweetService.postTweet(tweetModel)
    }
}

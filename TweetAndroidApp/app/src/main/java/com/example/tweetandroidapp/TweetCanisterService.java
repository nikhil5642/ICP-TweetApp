package com.example.tweetandroidapp;

import com.example.tweetandroidapp.models.TweetModel;

import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;
import java.util.concurrent.CompletableFuture;

public interface TweetCanisterService {

    @QUERY
    CompletableFuture<TweetModel[]> getTweets();

    @UPDATE
    CompletableFuture<Long> postTweet(TweetModel tweetModel);
}

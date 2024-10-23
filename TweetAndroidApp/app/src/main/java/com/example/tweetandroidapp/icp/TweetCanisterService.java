package com.example.tweetandroidapp.icp;

import com.example.tweetandroidapp.models.TweetModel;

import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;

import java.util.concurrent.CompletableFuture;

public interface TweetCanisterService {

    @QUERY
    TweetModel[] getTweets();

    @UPDATE
    CompletableFuture<Long> postTweet(TweetModel tweetModel);
}

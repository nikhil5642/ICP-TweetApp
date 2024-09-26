package com.example.tweetandroidapp.icp;

import com.example.tweetandroidapp.models.TweetModel;

import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;

public interface TweetCanisterService {

    @QUERY
    TweetModel[] getTweets();

    @UPDATE
    Long postTweet(TweetModel tweetModel);
}

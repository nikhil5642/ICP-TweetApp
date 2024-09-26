package com.example.tweetandroidapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tweetandroidapp.models.TweetModel;

import java.util.concurrent.CompletableFuture;

public class TweetViewModel extends ViewModel {
    private TweetCanisterService tweetService;
    
    private MutableLiveData<String> postResult = new MutableLiveData<>();
    private MutableLiveData<TweetModel[]> tweetList = new MutableLiveData<>();

    public TweetViewModel(TweetCanisterService tweetService) {
        this.tweetService = tweetService;
    }

    public LiveData<String> getPostResult() {
        return postResult;
    }

    public LiveData<TweetModel[]> getTweets() {
        return tweetList;
    }

    public void postTweet(String content, String userId) {
        long createdAt = System.currentTimeMillis();
        TweetModel tweetModel = new TweetModel(null, content, userId, createdAt);

        CompletableFuture<Long> future = tweetService.postTweet(tweetModel);
        future.thenAccept(tweetId -> postResult.postValue("Tweet posted with ID: " + tweetId))
              .exceptionally(throwable -> {
                  postResult.postValue("Failed to post tweet");
                  throwable.printStackTrace();
                  return null;
              });
    }

    public void loadTweets() {
        CompletableFuture<TweetModel[]> future = tweetService.getTweets();
        future.thenAccept(tweets -> tweetList.postValue(tweets))
              .exceptionally(throwable -> {
                  postResult.postValue("Failed to load tweets");
                  throwable.printStackTrace();
                  return null;
              });
    }
}

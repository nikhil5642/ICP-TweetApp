import Principal "mo:base/Principal";
import Time "mo:base/Time";
import Array "mo:base/Array";

actor TweetCanister {
    type Tweet = {
        id: Nat;
        creatorID: Text;  
        applicationID: Principal;  
        created: Int;
        content: Text;
    };

    var tweets: [Tweet] = [];
    var nextTweetId: Nat = 0;

    stable var stableTweets: [Tweet] = [];

    public shared(msg) func postTweet( userID: Text,content: Text) : async Nat {
        let newTweet: Tweet = {
            id = nextTweetId;
            creatorID = userID;  
            applicationID = msg.caller;  
            content = content;
            created = Time.now();
        };

        tweets := Array.append(tweets, [newTweet]);
        nextTweetId += 1;

        return newTweet.id;
    };

    public query func getTweets() : async [Tweet] {
        return tweets;
    };

    // Save tweets to stable memory for persistence across upgrades
    public func saveToStable() : async () {
        stableTweets := tweets;
    };

    // Load tweets from stable memory after upgrade
    public func loadFromStable() : async () {
        tweets := stableTweets;
    }
}

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

    // Function to post a tweet with custom user ID
    public shared(msg) func postTweet( userID: Text,content: Text) : async Nat {
        let newTweet: Tweet = {
            id = nextTweetId;
            creatorID = userID;  // Store the user-provided ID
            applicationID = msg.caller;  // Store the caller's principal for reference
            content = content;
            created = Time.now();
        };

        // Add the new tweet to the in-memory array
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

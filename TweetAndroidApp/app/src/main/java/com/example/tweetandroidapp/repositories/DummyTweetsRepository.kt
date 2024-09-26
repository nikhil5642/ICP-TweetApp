import android.content.Context
import android.content.SharedPreferences
import com.example.tweetandroidapp.icp.ICClient
import com.example.tweetandroidapp.models.TweetModel
import com.example.tweetandroidapp.repositories.TweetsRepository

/**
 * DummyTweetsRepository - This class is designed to test the TweetsRepository UI only without deploying actual canister.
 * This makes the UI testing faster and better.
 */
class DummyTweetsRepository(context: Context) : TweetsRepository(context){
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("tweets_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val TWEETS_KEY = "tweets"
        private const val TWEET_COUNT_KEY = "tweet_count"
    }

    override fun getTweets(): Array<TweetModel> {
        val tweetCount = sharedPreferences.getInt(TWEET_COUNT_KEY, 0)
        val tweets = mutableListOf<TweetModel>()

        for (i in 0 until tweetCount) {
            val tweetContent = sharedPreferences.getString("tweet_content_$i", null)
            val tweetCreatorId = sharedPreferences.getString("tweet_creatorId_$i", null)
            val tweetCreatedAt = sharedPreferences.getLong("tweet_createdAt_$i", 0L)

            if (tweetContent != null && tweetCreatorId != null && tweetCreatedAt != 0L) {
                tweets.add(
                    TweetModel(
                        id = i.toLong(),
                        content = tweetContent,
                        creatorId = tweetCreatorId,
                        createdAt = tweetCreatedAt
                    )
                )
            }
        }
        // Convert MutableList to Array
        return tweets.reversed().toTypedArray()
    }


    // Post a new tweet and store it in SharedPreferences
    override fun postTweet(userId: String?, content: String?): Long {
        val createdAt = System.currentTimeMillis()
        val tweetCount = sharedPreferences.getInt(TWEET_COUNT_KEY, 0)

        // Store tweet content and metadata
        sharedPreferences.edit().apply {
            putString("tweet_content_$tweetCount", content)
            putString("tweet_creatorId_$tweetCount", userId)
            putLong("tweet_createdAt_$tweetCount", createdAt)
            putInt(TWEET_COUNT_KEY, tweetCount + 1)
        }.apply()

        return createdAt
    }

}

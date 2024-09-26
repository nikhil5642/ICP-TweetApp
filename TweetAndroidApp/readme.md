Here’s a detailed **`README.md`** for your **Android module** of the **TweetApp** project, explaining the architecture, key components, and how the `ICClient` and `TweetCanisterService` interact with the Internet Computer:

---

## TweetApp - Android Module

This is the Android client for **TweetApp**, a decentralized Twitter-like platform that interacts with the Internet Computer (ICP). The app allows users to post tweets and view tweets using a custom user ID. The Android app is built with **MVVM architecture** and uses **IC4J** libraries to interact with the canister deployed on the Internet Computer.

### Table of Contents
- [Architecture](#architecture)
- [Technology](#technology)
- [Key Components](#key-components)
- [How It Works](#how-it-works)
- [Setup](#setup)
- [Usage](#usage)

---

### Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Contains the data and business logic (in this case, interaction with the canister).
- **View**: The UI components (activities, fragments).
- **ViewModel**: Manages UI-related data and handles communication between the view and model.

This pattern improves separation of concerns and makes the app easier to maintain.

---

### Technology

- **Kotlin**: Main language for the Android app.
- **MVVM**: Architecture used to separate logic from UI components.
- **IC4J**: Library for interacting with the Internet Computer (ICP) from Android.
- **OkHTTP**: Used for networking and interfacing with the ICP canister.
- **DataBinding**: For efficiently binding UI components to ViewModel data.

---

### Key Components

#### 1. **ICClient**

The `ICClient` is a singleton class that handles communication with the ICP canister. It initializes the **IC4J Agent** using an anonymous identity for now, and uses `ProxyBuilder` to generate the proxy for the `TweetCanisterService` interface.

```java
public class ICClient {
    private static ICClient instance;
    private final TweetCanisterService tweetService;

    private ICClient(String canisterId, String icEndpoint) throws Exception {
        Agent agent = new AgentBuilder()
            .identity(new AnonymousIdentity())
            .transport(ReplicaOkHttpTransport.create(icEndpoint))
            .build();

        tweetService = ProxyBuilder.create(agent, Principal.fromString(canisterId))
            .getProxy(TweetCanisterService.class);
    }

    public static ICClient getInstance(Context context) throws Exception {
        if (instance == null) {
            instance = new ICClient(
                context.getString(R.string.canister_id),
                context.getString(R.string.ic_endpoint)
            );
        }
        return instance;
    }

    public TweetCanisterService getTweetService() {
        return tweetService;
    }
}
```

#### 2. **TweetCanisterService**

This interface defines the methods to interact with the ICP canister. It includes two key methods:

- `getTweets()`: Retrieves the list of tweets from the canister.
- `postTweet(TweetModel tweetModel)`: Posts a new tweet to the canister.

```java
public interface TweetCanisterService {

    @QUERY
    TweetModel[] getTweets();

    @UPDATE
    Long postTweet(TweetModel tweetModel);
}
```

#### 3. **TweetModel**

The `TweetModel` represents a tweet in the application. This model is used both on the Android side and the ICP canister to manage tweet data:

```kotlin
data class TweetModel(
    @Field(Type.NAT)
    @Name("id")
    var id: Long? = null,

    @Field(Type.TEXT)
    @Name("content")
    var content: String? = null,

    @Field(Type.TEXT)
    @Name("creatorID")
    var creatorId: String? = null,

    @Field(Type.INT)
    @Name("createdAt")
    var createdAt: Long? = null
){
    fun getFormattedTime(): String? {
        val date = createdAt?.let { Date(it) }
        val format = SimpleDateFormat("h:mm a · MMM d, yyyy", Locale.getDefault())
        return date?.let { format.format(it) }
    }
}
```

---

### How It Works

1. **Posting a Tweet**:
   - The user writes a tweet in the Android app, which sends the tweet's content to the ICP canister via the `postTweet()` method in `TweetCanisterService`.
   - The `ICClient` sends the request to the canister using an anonymous identity.

2. **Retrieving Tweets**:
   - The `getTweets()` method in the `TweetCanisterService` retrieves the list of all tweets stored in the canister.
   - These tweets are displayed in the Android app using a `RecyclerView`.

---

### Setup

#### Prerequisites

- **Android Studio**: Make sure you have the latest version of Android Studio installed.
- **ICP Canister**: The backend canister must be deployed on the Internet Computer (follow the instructions in the [canister README](../canister/README.md)).

#### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/nikhil5642/ICP-TweetApp.git
   cd ICP-TweetApp/TweetAndroidApp
   ```

2. **Open in Android Studio**:
   - Open the project in **Android Studio** and let Gradle sync the dependencies.

3. **Set up the Canister ID and Endpoint**:
   - Open `res/values/strings.xml` and set the correct `canister_id` and `ic_endpoint` values:
     ```xml
     <string name="canister_id">your-canister-id</string>
     <string name="ic_endpoint">https://your-ic-endpoint-url</string>
     ```

4. **Build and Run the App**:
   - You can now build and run the app on an emulator or a physical device.

---

### Usage

#### Posting a Tweet

- **WriteTweetFragment**: This fragment allows the user to write and post a tweet. It uses data binding and interacts with the `WriteTweetViewModel` to send the tweet to the canister using the `postTweet()` method in the `TweetCanisterService`.

#### Viewing Tweets

- **HomeFragment**: This fragment displays the list of tweets retrieved from the canister. It uses a `RecyclerView` to show each tweet in a scrollable list. The data is fetched from the `TweetCanisterService` via the `getTweets()` method.


## Tweet Canister

This is the backend module for the **TweetApp** decentralized Twitter-like platform. The canister is built on the **Internet Computer (ICP)** using the **Motoko** programming language. It handles the core functionalities, including creating, storing, and retrieving tweets in a decentralized manner. Each tweet is tied to a custom user ID and the user's `Principal`, and all data is persistently stored in the canister.

### Table of Contents
- [Features](#features)
- [Technology](#technology)
- [Canister Functions](#canister-functions)
- [Setup and Deployment](#setup-and-deployment)
- [Usage](#usage)
- [Persistence](#persistence)

---

### Features

- **Post Tweets**: Users can post tweets using a custom user ID and content.
- **Retrieve Tweets**: Retrieve all tweets stored in the canister.
- **Persistent Storage**: Tweets are stored in stable memory, ensuring they are retained even after canister upgrades.

---

### Technology

- **Motoko**: The backend logic for the canister is written in Motoko.
- **Internet Computer (ICP)**: The canister is deployed on the decentralized Internet Computer network.
- **DFX SDK**: Used for canister management, deployment, and interaction.

---

### Canister Functions

#### `postTweet(userID: Text, content: Text) : async Nat`
- **Purpose**: Allows users to post a tweet with their custom user ID and content.
- **Parameters**:
  - `userID`: A custom identifier for the user posting the tweet.
  - `content`: The content of the tweet.
- **Returns**: The unique ID of the tweet.
- **Example Usage**:
  ```motoko
  let tweetID = await postTweet("user123", "This is my first tweet!");
  ```

#### `getTweets() : query async [Tweet]`
- **Purpose**: Returns all tweets stored in the canister.
- **Returns**: An array of `Tweet` objects.
- **Example Usage**:
  ```motoko
  let allTweets = await getTweets();
  ```

#### `saveToStable() : async ()`
- **Purpose**: Saves the current in-memory tweets to stable storage, ensuring persistence across upgrades.
  
#### `loadFromStable() : async ()`
- **Purpose**: Loads the tweets from stable storage back into memory, typically used after a canister upgrade.

---

### Data Structure

The **Tweet** data type is structured as follows:

```motoko
type Tweet = {
    id: Nat;
    creatorID: Text;       // The custom user ID provided by the user.
    applicationID: Principal;  // The Principal of the user (for reference).
    created: Int;          // The timestamp when the tweet was created.
    content: Text;         // The actual content of the tweet.
};
```

---

### Setup and Deployment

To deploy the **Tweet Canister** on the **Internet Computer**, follow these steps:

#### Prerequisites

- **DFX SDK**: Ensure the DFX SDK is installed on your machine. Follow the official guide [here](https://smartcontracts.org/docs/quickstart/quickstart-intro.html).
  
#### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/tweetapp-icp.git
   cd tweetapp-icp/canister
   ```

2. **Start the DFX environment**:
   Start the Internet Computer locally.
   ```bash
   dfx start --background
   ```

3. **Deploy the Canister**:
   Deploy the canister to the local network:
   ```bash
   dfx deploy
   ```

4. **Interact with the Canister**:
   Once deployed, you can interact with the canister using either DFX commands or an external application (e.g., the Android app).
   ```bash
   dfx canister call tweet-canister postTweet '("user123", "Hello World!")'
   ```

---

### Usage

The `tweet-canister` allows external applications (such as the accompanying Android app) to interact with it by posting and retrieving tweets. The following actions can be performed via canister API calls:

- **Post a Tweet**: Users can post tweets using their user ID and the content.
- **Get Tweets**: Retrieve the list of all tweets stored in the canister.

These actions can be invoked programmatically or via a front-end application (like the Android app).

---

### Persistence

The canister stores tweets in memory but also persists them across upgrades using **stable variables**:

- **Stable Variables**: The `stableTweets` variable is used to persist the tweets across canister upgrades.
- Before upgrading the canister, make sure to invoke the `saveToStable()` function to save the tweets.
  
---


### Notes

- This canister is designed to be highly scalable, supporting any number of users posting tweets. The use of `Array.append` ensures that new tweets are appended to the in-memory array efficiently.
- The user’s Principal (`applicationID`) is also stored for reference, but the primary identifier for each tweet is the custom `userID`.

# TweetApp - Decentralized Twitter on Internet Computer

This is a decentralized tweet platform built on **Internet Computer** (ICP) using a combination of a backend **canister** and an **Android application**. Users can post tweets and view other tweets on the network using a custom user ID. The backend stores the tweets persistently using a stable variable in the canister.

https://github.com/user-attachments/assets/0b1cf6e6-a2ad-4f46-8f34-7b99611079cd

## Project Structure

- **tweet-canister/**: The backend canister code written in Motoko that handles tweet storage and retrieval.
- **TweetAndroidApp/**: The Android app written in Kotlin that allows users to interact with the canister by posting tweets and viewing existing tweets.

## Features

- Users can post tweets using a custom user ID and content.
- Tweets are stored on the Internet Computer (ICP) and can be retrieved.
- The Android app interfaces with the canister, allowing users to post, view and React to tweets.

## Technology Stack

- **Internet Computer (ICP)**: Decentralized backend hosted on a canister.
- **Motoko**: Language used for writing the backend canister.
- **Kotlin**: Android app written in Kotlin with MVVM architecture.
- **IC4J**: Library for interacting with ICP from the Android app.

## Installation

### Prerequisites

- DFX (Dfinity SDK) to deploy the canister.
- Android Studio is used to build and run the Android application.

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/nikhil5642/ICP-TweetApp.git
   cd ICP-TweetApp
   ```
2. Set up the canister:
   ```bash
   Copy code
   cd tweet-canister
   dfx start --background
   dfx deploy
   ```
3. Build the Android app:
  - Open the Android folder in Android Studio.
  - Sync Gradle files and run the app on a connected device/emulator.

### Contributing
Feel free to submit a pull request or raise issues.

package com.example.tweetandroidapp.repositories

import android.content.Context

class UserRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val USER_ID = "user_id"
    }

    // Save the user ID to SharedPreferences
    fun saveUserId(id: String) {
        with(sharedPreferences.edit()) {
            putString(USER_ID, id)
            apply()
        }
    }

    // Retrieve the user ID from SharedPreferences
    fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID, null)
    }

    // Clear the user ID from SharedPreferences
    fun clearUserId() {
        with(sharedPreferences.edit()) {
            remove(USER_ID)
            apply()
        }
    }
}

package com.example.tweetandroidapp.views.login

import com.example.tweetandroidapp.repositories.UserRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface UserActionListener{
    fun onLoginSuccess()
    fun onLogoutSuccess()
}

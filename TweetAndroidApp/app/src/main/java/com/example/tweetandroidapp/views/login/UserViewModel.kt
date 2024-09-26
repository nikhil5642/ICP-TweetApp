package com.example.tweetandroidapp.views.login

import com.example.tweetandroidapp.repositories.UserRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val username = MutableLiveData<String>()

    init{
        username.value = userRepository.getUserId()
    }
    fun onContinueClicked() {
        val currentUsername = username.value ?: return
        userRepository.saveUserId(currentUsername)
    }
}

package com.example.tweetandroidapp.views.login

import com.example.tweetandroidapp.repositories.UserRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val userRepository: UserRepository,
                    private val userActionListener: UserActionListener) : ViewModel() {
    val userId = MutableLiveData<String>()

    init{
        fetchUserId()
    }

    private fun fetchUserId() {
        userId.value = userRepository.getUserId()
    }

    // Set userId in shared preferences
    fun login() {
        userId.value?.let{
            userRepository.saveUserId(it)
            userActionListener.onLoginSuccess()
        }
    }

    fun logOut(){
        userRepository.clearUserId()
        userId.value=""
        userActionListener.onLogoutSuccess()
    }
}

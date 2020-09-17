package com.example.cookbook.presentation

import androidx.lifecycle.ViewModel
import com.example.cookbook.domain.UserRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun isUserLoggedIn(): Boolean {
        val user = userRepository.getUser()
        val currentTime = Calendar.getInstance().time
        val loggedIn = currentTime < user.token.expireDate
        Timber.d(loggedIn.toString())
        return loggedIn
    }

}
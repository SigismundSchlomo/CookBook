package com.example.cookbook.presentation.authflow

import androidx.lifecycle.ViewModel
import com.example.cookbook.auth.Auth
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val auth: Auth) : ViewModel() {

    fun login(email: String, password: String) {
        auth.login(email, password)
    }

    fun createAccount(email: String, password: String, displayName: String) {
        auth.createAccount(email, password, displayName)
    }

}
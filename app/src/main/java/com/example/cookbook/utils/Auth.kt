package com.example.cookbook.utils

import com.example.cookbook.data.network.AuthNetworkService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Auth @Inject constructor(private val networkService: AuthNetworkService) {

    private var token: String? = null

    fun login(email: String, password: String) = runBlocking {
        token = networkService.login(password, email)
    }

    fun createAccount(email: String, password: String, displayName: String) = runBlocking {
        token = networkService.createUser(password, displayName, email)
    }

    fun isLoggedIn(): Boolean {
        return token != null
    }

    fun loggedOut() {
        token = null
    }

    fun getToken(): String {
        if (token != null) {
            return token!!
        } else {
            throw IllegalAccessError("User logged out")
        }
    }

}
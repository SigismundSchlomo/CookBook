package com.example.cookbook.data.network

import com.example.cookbook.data.network.models.AuthRequest
import com.example.cookbook.domain.AuthNetwork
import com.example.cookbook.domain.Token
import com.example.cookbook.domain.User
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AuthNetworkImpl @Inject constructor(private val authNetworkService: AuthNetworkService) :
    AuthNetwork {

    //TODO: retrieve user from sever (available after server version 0.4)
    override suspend fun login(email: String, password: String): User {
        val authRequest = AuthRequest(email = email, password = password)
        val tokenStr = authNetworkService.login(authRequest)
        Timber.d(tokenStr)

        val currentDate = Calendar.getInstance().time
        val expireData = Date(currentDate.time + (3_600_000 * 23)) // Expire after 23 hours
        val token = Token(tokenStr, expireData)

        return User(email = email, token = token)
    }

    //TODO: retrieve user from sever (available after server version 0.4)
    override suspend fun createUser(email: String, password: String, name: String): User {
        val authRequest = AuthRequest(email, name, password)
        val tokenStr = authNetworkService.createUser(authRequest)

        val currentDate = Calendar.getInstance().time
        val expireData = Date(currentDate.time + (3_600_000 * 23)) // Expire after 23 hours
        val token = Token(tokenStr, expireData)

        return User(email = email, token = token)
    }
}
package com.example.cookbook.data.network.auth

import android.accounts.NetworkErrorException
import com.example.cookbook.data.network.auth.models.AuthRequestData
import com.example.cookbook.domain.AuthNetwork
import com.example.cookbook.domain.models.Token
import com.example.cookbook.domain.models.User
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AuthNetworkImpl @Inject constructor(private val authNetworkService: AuthNetworkService) :
    AuthNetwork {

    override suspend fun login(email: String, password: String): User {
        val authRequest = AuthRequestData(email = email, password = password)
        val response = authNetworkService.login(authRequest)

        if (response.isSuccessful) {

            val responseData = response.body()!!
            val tokenStr = responseData.token
            val token = generateTokenFromString(tokenStr)
            return User(
                responseData.userId,
                responseData.email,
                responseData.userName,
                token
            )

        } else {
            val error = response.errorBody().toString()
            Timber.d(error)
            throw NetworkErrorException(error)
        }
    }

    //TODO: retrieve user from sever (available after server version 0.4)
    override suspend fun createUser(email: String, password: String, name: String): User {
        val authRequest = AuthRequestData(email, name, password)
        val response = authNetworkService.createUser(authRequest)

        if (response.isSuccessful) {

            val responseData = response.body()!!
            val tokenStr = responseData.token
            val token = generateTokenFromString(tokenStr)

            return User(
                responseData.userId,
                responseData.email,
                responseData.userName,
                token
            )
        } else {
            val error = response.errorBody().toString()
            Timber.d(error)
            throw NetworkErrorException(error)
        }
    }

    private fun generateTokenFromString(tokenStr: String): Token {
        val currentDate = Calendar.getInstance().time
        val expireData = Date(currentDate.time + (3_600_000 * 23)) // Expire after 23 hours
        return Token(tokenStr, expireData)
    }

}
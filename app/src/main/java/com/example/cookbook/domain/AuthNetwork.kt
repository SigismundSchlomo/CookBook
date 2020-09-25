package com.example.cookbook.domain

import com.example.cookbook.domain.models.User

interface AuthNetwork {

    suspend fun login(email: String, password: String): User
    suspend fun createUser(email: String, password: String, name: String): User

}
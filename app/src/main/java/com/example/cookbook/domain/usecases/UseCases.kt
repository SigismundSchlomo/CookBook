package com.example.cookbook.domain.usecases

import com.example.cookbook.domain.AuthNetwork
import com.example.cookbook.domain.UserRepository
import com.example.cookbook.domain.models.User
import javax.inject.Inject

class UseCases @Inject constructor(
    private val userRepository: UserRepository,
    private val networkService: AuthNetwork
) {
    suspend fun loginUser(email: String, password: String): User {
        val user = networkService.login(email, password)
        userRepository.saveUser(user)
        return user
    }

    suspend fun createUser(email: String, password: String, name: String): User {
        val user = networkService.createUser(email, password, name)
        userRepository.saveUser(user)
        return user
    }

    fun getCurrentUser(): User {
        return userRepository.getUser()!!
    }

}
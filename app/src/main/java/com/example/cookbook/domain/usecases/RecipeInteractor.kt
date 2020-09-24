package com.example.cookbook.domain.usecases

import com.example.cookbook.domain.UserRepository
import javax.inject.Inject

class RecipeInteractor @Inject constructor(private val userRepository: UserRepository) {

    fun logoutUser() {
        userRepository.deleteUser()
    }

}

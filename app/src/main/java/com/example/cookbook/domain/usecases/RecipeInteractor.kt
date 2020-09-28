package com.example.cookbook.domain.usecases

import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.UserRepository
import com.example.cookbook.domain.models.Recipe
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val recipeRepository: RecipeRepository
) {

    fun logoutUser() {
        userRepository.deleteUser()
    }

    suspend fun createRecipe(header: String, body: String) {
        recipeRepository.createRecipe(header, body)
    }

    suspend fun loadRecipesFromNetwork(): List<Recipe> {
        return recipeRepository.getRecipes()
    }

    suspend fun loadRecipesFromDatabase(): List<Recipe> {
        return recipeRepository.getFromDatabase()
    }
}
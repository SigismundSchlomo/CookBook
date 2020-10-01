package com.example.cookbook.domain.usecases

import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.UserRepository
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.domain.models.User
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val userRepository: UserRepository,
    private val recipeRepository: RecipeRepository
) {

    fun getCurrentUser(): User {
        return userRepository.getUser()!!
    }

    fun logoutUser() {
        userRepository.deleteUser()
    }

    suspend fun createRecipe(recipe: Recipe) {
        recipeRepository.createRecipe(recipe)
    }

    suspend fun loadRecipesFromNetwork(): List<Recipe> {
        return recipeRepository.getRecipes()
    }

    suspend fun loadRecipesFromDatabase(): List<Recipe> {
        return recipeRepository.getFromDatabase()
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeRepository.deleteRecipe(recipe)
    }

}
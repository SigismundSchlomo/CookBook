package com.example.cookbook.domain

import com.example.cookbook.domain.models.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun createRecipe(header: String, body: String)
    suspend fun getFromDatabase(): List<Recipe>
    suspend fun deleteRecipe(recipe: Recipe)
}
package com.example.cookbook.domain

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun createRecipe()
    suspend fun enableCaching(): Boolean
}
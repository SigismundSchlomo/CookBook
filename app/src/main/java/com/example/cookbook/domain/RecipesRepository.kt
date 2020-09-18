package com.example.cookbook.domain

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun createRecipe(header: String, body: String)
    suspend fun getFromDatabase(): List<Recipe>
}
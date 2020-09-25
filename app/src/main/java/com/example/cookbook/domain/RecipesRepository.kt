package com.example.cookbook.domain

import com.example.cookbook.domain.models.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun createRecipe(header: String, body: String)
    suspend fun getFromDatabase(): List<Recipe>
}
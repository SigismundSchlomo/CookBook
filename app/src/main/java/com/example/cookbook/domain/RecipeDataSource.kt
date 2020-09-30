package com.example.cookbook.domain

import com.example.cookbook.domain.models.Recipe

interface RecipeDataSource {

    suspend fun getRecipes(): List<Recipe>
    suspend fun saveRecipe(recipe: Recipe)
    suspend fun saveAll(recipes: List<Recipe>)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun updateRecipe(recipe: Recipe)

}
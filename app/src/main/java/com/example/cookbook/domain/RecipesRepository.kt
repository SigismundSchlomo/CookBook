package com.example.cookbook.domain

interface RecipesRepository {
    fun getRecipes(): List<Recipe>
    fun createRecipe()
    fun enableCaching(): Boolean
}
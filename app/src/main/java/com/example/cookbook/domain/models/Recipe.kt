package com.example.cookbook.domain.models

data class Recipe(
    val id: Int = 0,
    val header: String,
    val body: String,
    val ingredients: List<Ingredient> = emptyList(),
    val cookingSteps: List<CookingStep> = emptyList()
)
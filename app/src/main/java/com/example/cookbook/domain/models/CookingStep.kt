package com.example.cookbook.domain.models

data class CookingStep(
    val id: Int = 0,
    val recipeId: Int = 0,
    val description: String
)
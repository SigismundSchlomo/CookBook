package com.example.cookbook.domain.models

data class CookingStep(
    val id: Int,
    val recipeId: Int,
    val description: String
)
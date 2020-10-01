package com.example.cookbook.domain.models

data class Ingredient(
    val id: Int = 0,
    val userId: Int,
    val recipeId: Int = 0,
    val name: String,
    val quantity: String,
    val isInTheList: Boolean
)
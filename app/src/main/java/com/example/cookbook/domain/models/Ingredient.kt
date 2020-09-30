package com.example.cookbook.domain.models

data class Ingredient(
    val id: Int,
    val userId: Int,
    val recipeId: Int,
    val name: String,
    val quantity: String,
    val isInTheList: Boolean
)
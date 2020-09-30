package com.example.cookbook.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbIngredient(

    @PrimaryKey val ingredientId: Int,
    val userId: Int,
    val recipeId: Int,
    val name: String,
    val quantity: String,
    val isInTheList: Boolean

)
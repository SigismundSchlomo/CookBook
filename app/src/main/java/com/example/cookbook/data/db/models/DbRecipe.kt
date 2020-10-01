package com.example.cookbook.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbRecipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    val header: String,
    val body: String
)
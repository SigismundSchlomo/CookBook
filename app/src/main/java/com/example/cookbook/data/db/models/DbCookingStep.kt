package com.example.cookbook.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbCookingStep(
    @PrimaryKey val stepId: Int,
    val recipeId: Int,
    val description: String
)
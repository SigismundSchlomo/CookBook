package com.example.cookbook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookbook.data.db.models.DbCookingStep
import com.example.cookbook.data.db.models.DbIngredient
import com.example.cookbook.data.db.models.DbRecipe

@Database(
    entities = arrayOf(DbRecipe::class, DbIngredient::class, DbCookingStep::class),
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
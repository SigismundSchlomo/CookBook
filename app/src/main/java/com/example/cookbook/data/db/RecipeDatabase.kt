package com.example.cookbook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookbook.domain.Recipe

@Database(entities = arrayOf(Recipe::class), version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
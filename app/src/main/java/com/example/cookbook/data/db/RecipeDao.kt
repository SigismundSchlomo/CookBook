package com.example.cookbook.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cookbook.domain.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    suspend fun getAll(): List<Recipe>

    @Insert
    suspend fun insertAll(recipes: List<Recipe>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("DELETE FROM Recipe")
    suspend fun nukeTable()

}
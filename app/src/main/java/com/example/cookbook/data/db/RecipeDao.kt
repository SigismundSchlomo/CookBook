package com.example.cookbook.data.db

import androidx.room.*
import com.example.cookbook.domain.models.Recipe

@Dao()
interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    suspend fun getAll(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes: List<Recipe>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("DELETE FROM Recipe")
    suspend fun nukeTable()

}
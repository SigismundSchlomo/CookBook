package com.example.cookbook.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.cookbook.domain.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    fun getAll(): List<Recipe>

    @Insert
    fun insertAll(recipes: List<Recipe>)

    @Delete
    fun delete(recipe: Recipe)

    @Query("DELETE FROM Recipe")
    fun nukeTable()

}
package com.example.cookbook.data.db

import androidx.room.*
import com.example.cookbook.data.db.models.DbCookingStep
import com.example.cookbook.data.db.models.DbIngredient
import com.example.cookbook.data.db.models.DbRecipe
import com.example.cookbook.data.db.models.RecipeWithReferences

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM DbRecipe")
    suspend fun getAll(): List<RecipeWithReferences>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: DbRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<DbIngredient>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCookingSteps(cookingSteps: List<DbCookingStep>)

    @Delete
    suspend fun deleteRecipe(recipe: DbRecipe)

    @Delete
    suspend fun deleteIngredients(ingredients: List<DbIngredient>)

    @Delete
    suspend fun deleteCookingSteps(cookingSteps: List<DbCookingStep>)

    @Update
    suspend fun updateRecipe(recipe: DbRecipe)

    @Update
    suspend fun updateIngredients(ingredients: List<DbIngredient>)

    @Update
    suspend fun updateCookingSteps(cookingSteps: List<DbCookingStep>)

    @Query("DELETE FROM DbRecipe")
    suspend fun nukeTable()

}
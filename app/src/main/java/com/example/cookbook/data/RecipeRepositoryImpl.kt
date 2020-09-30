package com.example.cookbook.data

import com.example.cookbook.data.db.RecipeDbDataSource
import com.example.cookbook.data.network.RecipeNetworkDataSource
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val network: RecipeNetworkDataSource,
    private val db: RecipeDbDataSource
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        try {
            val recipes = network.getRecipes()
            withContext(Dispatchers.IO) { db.saveAll(recipes) }
            return recipes
        } catch (t: Throwable) {
            throw t
        }
    }

    override suspend fun createRecipe(header: String, body: String) {
        val recipe = Recipe(
            header = header,
            body = body
        )
        network.saveRecipe(recipe)
    }

    override suspend fun getFromDatabase(): List<Recipe> {
        return db.getRecipes()
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        db.deleteRecipe(recipe)
        network.deleteRecipe(recipe)
    }

}
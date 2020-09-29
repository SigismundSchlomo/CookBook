package com.example.cookbook.data

import android.accounts.NetworkErrorException
import com.example.cookbook.data.db.RecipeDao
import com.example.cookbook.data.network.RecipesNetworkService
import com.example.cookbook.domain.RecipeRepository
import com.example.cookbook.domain.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesNetworkService: RecipesNetworkService,
    private val db: RecipeDao
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val response = recipesNetworkService.getRecipes()

        if (response.isSuccessful) {
            val recipes = response.body()!!
            withContext(Dispatchers.IO) { db.insertAll(recipes) }
            return recipes
        } else {
            val error = response.errorBody().toString()
            Timber.d(error)
            throw NetworkErrorException(error)
        }
    }

    override suspend fun createRecipe(header: String, body: String) {
        val recipe = Recipe(
            header = header,
            body = body
        )
        recipesNetworkService.postRecipe(recipe)
    }

    override suspend fun getFromDatabase(): List<Recipe> {
        return db.getAll()
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        db.delete(recipe)
        recipesNetworkService.deleteRecipe(recipe)
    }

}
package com.example.cookbook.data.network.recipeservice

import android.accounts.NetworkErrorException
import com.example.cookbook.domain.RecipeDataSource
import com.example.cookbook.domain.models.Recipe
import timber.log.Timber

class RecipeNetworkDataSource(private val networkService: RecipesNetworkService) :
    RecipeDataSource {

    override suspend fun getRecipes(): List<Recipe> {
        val response = networkService.getRecipes()

        if (response.isSuccessful) {
            return response.body()?.map { it.toRecipe() }!!
        } else {
            val error = response.errorBody().toString()
            Timber.d(error)
            throw NetworkErrorException(error)
        }

    }

    override suspend fun saveRecipe(recipe: Recipe) {
        networkService.postRecipe(recipe.toNtRecipe())
    }

    override suspend fun saveAll(recipes: List<Recipe>) {
        recipes.forEach { networkService.postRecipe(it.toNtRecipe()) }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        networkService.deleteRecipe(recipe.toNtRecipe())
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        TODO("Not implemented on server side")
    }
}
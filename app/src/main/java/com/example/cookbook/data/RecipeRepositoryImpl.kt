package com.example.cookbook.data

import com.example.cookbook.data.db.RecipeDao
import com.example.cookbook.data.network.RecipesNetworkService
import com.example.cookbook.domain.RecipesRepository
import com.example.cookbook.domain.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesNetworkService: RecipesNetworkService,
    private val db: RecipeDao
) : RecipesRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val recipes = recipesNetworkService.getRecipes()
        withContext(Dispatchers.IO) { db.insertAll(recipes) }
        return recipesNetworkService.getRecipes()
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

}
package com.example.cookbook.data

import com.example.cookbook.data.db.RecipeDao
import com.example.cookbook.domain.Recipe
import com.example.cookbook.domain.RecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val db: RecipeDao
) : RecipesRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val recipes = networkService.getRecipes()
        withContext(Dispatchers.IO) { db.insertAll(recipes) }
        return networkService.getRecipes()
    }

    override suspend fun createRecipe(header: String, body: String) {
        val recipe = Recipe(header = header, body = body)
        networkService.postRecipe(recipe)
    }

    override suspend fun getFromDatabase(): List<Recipe> {
        return db.getAll()
    }

    override suspend fun enableCaching(): Boolean {
        TODO("Not yet implemented")
    }
}
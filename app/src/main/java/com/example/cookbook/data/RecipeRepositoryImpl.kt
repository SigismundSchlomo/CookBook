package com.example.cookbook.data

import com.example.cookbook.domain.Recipe
import com.example.cookbook.domain.RecipesRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) :
    RecipesRepository {

    override suspend fun getRecipes(): List<Recipe> {
        return networkService.getRecipes()
    }

    override suspend fun createRecipe(header: String, body: String) {
        val recipe = Recipe(header = header, body = body)
        networkService.postRecipe(recipe)
    }

    override suspend fun enableCaching(): Boolean {
        TODO("Not yet implemented")
    }

}
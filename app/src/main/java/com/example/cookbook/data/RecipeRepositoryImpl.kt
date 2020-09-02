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

    override suspend fun createRecipe() {
        TODO("Not yet implemented")
    }

    override suspend fun enableCaching(): Boolean {
        TODO("Not yet implemented")
    }

}
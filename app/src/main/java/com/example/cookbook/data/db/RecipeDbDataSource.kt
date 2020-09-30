package com.example.cookbook.data.db

import com.example.cookbook.domain.RecipeDataSource
import com.example.cookbook.domain.models.Recipe

class RecipeDbDataSource(private val dao: RecipeDao) : RecipeDataSource {

    override suspend fun getRecipes(): List<Recipe> {
        return dao.getAll().map { it.toRecipe() }
    }

    override suspend fun saveAll(recipes: List<Recipe>) {
        recipes.forEach { saveRecipe(it) }
    }

    override suspend fun saveRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe.toDbRecipe())
        dao.insertIngredients(recipe.ingredients.map { it.toDbIngredient() })
        dao.insertCookingSteps(recipe.cookingSteps.map { it.toDbCookingStep() })
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe.toDbRecipe())
        dao.deleteIngredients(recipe.ingredients.map { it.toDbIngredient() })
        dao.deleteCookingSteps(recipe.cookingSteps.map { it.toDbCookingStep() })
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(recipe.toDbRecipe())
        dao.updateIngredients(recipe.ingredients.map { it.toDbIngredient() })
        dao.updateCookingSteps(recipe.cookingSteps.map { it.toDbCookingStep() })
    }
}
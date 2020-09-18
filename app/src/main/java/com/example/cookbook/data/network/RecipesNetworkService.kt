package com.example.cookbook.data.network

import com.example.cookbook.domain.models.Recipe
import retrofit2.http.*

private const val RECIPES = "/recipes"

interface RecipesNetworkService {

    @GET("/recipes")
    suspend fun getRecipes(): List<Recipe>

    @POST("/recipes")
    suspend fun postRecipe(@Body recipe: Recipe)

    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: String)

    @PATCH("recipes/{id}")
    suspend fun updateRecipe(@Path("id") id: String, @Body recipe: Recipe)

}
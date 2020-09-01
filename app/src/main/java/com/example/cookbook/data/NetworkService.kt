package com.example.cookbook.data

import com.example.cookbook.domain.Recipe
import retrofit2.http.*

private const val RECIPES = "/recipes"

interface NetworkService {

    @GET("/recipes")
    suspend fun getRecipes(): List<Recipe>

    @POST("/recipes")
    suspend fun postRecipe(@Body recipe: Recipe)

    @DELETE("recipes/{id}")
    suspend fun deleteRecipe(@Path("id") id: String)

    @PATCH("recipes/{id}")
    suspend fun updateRecipe(@Path("id") id: String, @Body recipe: Recipe)

}
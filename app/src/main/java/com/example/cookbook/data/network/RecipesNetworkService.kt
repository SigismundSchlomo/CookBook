package com.example.cookbook.data.network

import com.example.cookbook.domain.models.Recipe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface RecipesNetworkService {

    @GET("/recipes")
    suspend fun getRecipes(): Response<List<Recipe>>

    @POST("/recipes")
    suspend fun postRecipe(@Body recipe: Recipe)

    //DELETE
    @HTTP(method = "DELETE", path = "/recipes", hasBody = true)
    suspend fun deleteRecipe(@Body recipe: Recipe)

}
package com.example.cookbook.data.network.recipeservice

import com.example.cookbook.data.network.recipeservice.models.NtRecipe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface RecipesNetworkService {

    @GET("/recipes")
    suspend fun getRecipes(): Response<List<NtRecipe>>

    @POST("/recipes")
    suspend fun postRecipe(@Body recipe: NtRecipe)

    //DELETE Recipe
    @HTTP(method = "DELETE", path = "/recipes", hasBody = true)
    suspend fun deleteRecipe(@Body recipe: NtRecipe)

}
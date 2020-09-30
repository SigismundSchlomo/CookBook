package com.example.cookbook.data.network.recipeservice.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NtRecipe(

    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "header")
    val header: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "ingredients")
    val ingredients: List<NtIngredient> = emptyList(),
    @Json(name = "cookingSteps")
    val cookingSteps: List<NtCookingStep> = emptyList()
)
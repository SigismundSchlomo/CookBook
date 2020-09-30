package com.example.cookbook.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe(

    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "header")
    val header: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "ingredients")
    val ingredients: List<Ingredient> = emptyList(),
    @Json(name = "cookingSteps")
    val cookingSteps: List<CookingStep> = emptyList()
)
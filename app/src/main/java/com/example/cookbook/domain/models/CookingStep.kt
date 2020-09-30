package com.example.cookbook.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CookingStep(
    @Json(name = "id")
    val id: Int,
    @Json(name = "recipeId")
    val recipeId: Recipe,
    @Json(name = "description")
    val description: String
)
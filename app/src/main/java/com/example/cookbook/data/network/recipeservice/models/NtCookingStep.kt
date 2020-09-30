package com.example.cookbook.data.network.recipeservice.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NtCookingStep(
    @Json(name = "id")
    val id: Int,
    @Json(name = "recipeId")
    val recipeId: Int,
    @Json(name = "description")
    val description: String
)
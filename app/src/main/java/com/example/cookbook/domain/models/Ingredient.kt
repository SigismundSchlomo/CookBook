package com.example.cookbook.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    @Json(name = "id")
    val id: Int,
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "recipeId")
    val recipeId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "quantity")
    val quantity: String,
    @Json(name = "isInTheList")
    val isInTheList: Boolean
)
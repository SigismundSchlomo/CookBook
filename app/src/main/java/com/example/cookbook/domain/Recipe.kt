package com.example.cookbook.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "header")
    val header: String,
    @Json(name = "body")
    val body: String
)
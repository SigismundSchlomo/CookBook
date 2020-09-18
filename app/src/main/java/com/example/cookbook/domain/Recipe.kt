package com.example.cookbook.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Recipe(

    @PrimaryKey
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "header")
    val header: String,
    @Json(name = "body")
    val body: String
)
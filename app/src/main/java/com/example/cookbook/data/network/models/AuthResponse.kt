package com.example.cookbook.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    val token: String,
    val userId: Int,
    val userName: String,
    val email: String
)
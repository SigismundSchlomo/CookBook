package com.example.cookbook.data.network.auth.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponseData(
    val token: String,
    val userId: Int,
    val userName: String,
    val email: String
)
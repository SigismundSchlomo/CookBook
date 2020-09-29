package com.example.cookbook.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponseData(
    val token: String,
    val userId: Int,
    val userName: String,
    val email: String
)
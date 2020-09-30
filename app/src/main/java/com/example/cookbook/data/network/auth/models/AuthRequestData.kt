package com.example.cookbook.data.network.auth.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequestData(
    @Json(name = "email")
    val email: String,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "password")
    val password: String

)
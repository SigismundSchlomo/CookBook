package com.example.cookbook.domain.models

data class User(
    val id: Int = 0, //TODO: take away default value
    val email: String = "", //TODO: take away default value
    val name: String = "",
    val token: Token
)
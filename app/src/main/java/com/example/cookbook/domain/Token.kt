package com.example.cookbook.domain

import java.util.*

data class Token(
    val value: String,
    val expireDate: Date
)
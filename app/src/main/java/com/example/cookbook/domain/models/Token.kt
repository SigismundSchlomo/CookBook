package com.example.cookbook.domain.models

import java.util.*

data class Token(
    val value: String,
    val expireDate: Date
)

fun Token.isValid(currentDate: Date): Boolean {
    return (value.isNotEmpty() && expireDate > currentDate)
}
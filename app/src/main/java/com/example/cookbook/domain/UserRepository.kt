package com.example.cookbook.domain

import com.example.cookbook.domain.models.Token
import com.example.cookbook.domain.models.User

interface UserRepository {

    fun getUser(): User?
    fun saveUser(user: User)
    fun deleteUser()
    fun getToken(): Token

}
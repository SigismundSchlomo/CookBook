package com.example.cookbook.domain

import com.example.cookbook.domain.models.User

interface UserDataSource {

    fun getUser(): User?
    fun saveUser(user: User)
    fun deleteUser()

}
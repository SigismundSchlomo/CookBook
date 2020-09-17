package com.example.cookbook.domain

interface UserRepository {

    fun getUser(): User
    fun saveUser(user: User)
    fun deleteUser()
    fun getToken(): Token

}
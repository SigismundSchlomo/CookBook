package com.example.cookbook.domain

interface UserDataSource {

    fun getUser(): User?
    fun saveUser(user: User)
    fun deleteUser()

}
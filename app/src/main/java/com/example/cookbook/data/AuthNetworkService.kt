package com.example.cookbook.data

import retrofit2.http.POST
import retrofit2.http.Query

private const val USERS = "/users"

interface AuthNetworkService {

    @POST("$USERS/login")
    suspend fun login(
        @Query("password") password: String,
        @Query("email") email: String
    ): String


    @POST("$USERS/create")
    suspend fun createUser(
        @Query("password") password: String,
        @Query("displayName") displayName: String,
        @Query("email") email: String
    ): String

}
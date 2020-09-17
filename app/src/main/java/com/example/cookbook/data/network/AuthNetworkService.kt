package com.example.cookbook.data.network

import com.example.cookbook.data.network.models.AuthRequest
import com.example.cookbook.data.network.models.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

private const val USERS = "/users"

interface AuthNetworkService {

    @POST("$USERS/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @POST("$USERS/create")
    suspend fun createUser(@Body request: AuthRequest): AuthResponse

}
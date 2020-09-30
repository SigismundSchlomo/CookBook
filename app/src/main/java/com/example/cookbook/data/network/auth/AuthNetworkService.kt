package com.example.cookbook.data.network.auth

import com.example.cookbook.data.network.auth.models.AuthRequestData
import com.example.cookbook.data.network.auth.models.AuthResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val USERS = "/users"

interface AuthNetworkService {

    @POST("$USERS/login")
    suspend fun login(@Body requestData: AuthRequestData): Response<AuthResponseData>

    @POST("$USERS/create")
    suspend fun createUser(@Body requestData: AuthRequestData): Response<AuthResponseData>

}
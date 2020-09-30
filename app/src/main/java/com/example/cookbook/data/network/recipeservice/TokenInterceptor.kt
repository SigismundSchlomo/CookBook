package com.example.cookbook.data.network.recipeservice

import com.example.cookbook.domain.UserRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val userRepository: UserRepository) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val originalUrl = original.url

        val requestBuilder = original.newBuilder().apply {
            val token = userRepository.getToken().value
            addHeader("Authorization", "Bearer $token")
            url(originalUrl)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

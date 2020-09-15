package com.example.cookbook.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val originalUrl = original.url

        val requestBuilder = original.newBuilder().apply {
            addHeader("Authorization", token)
            url(originalUrl)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

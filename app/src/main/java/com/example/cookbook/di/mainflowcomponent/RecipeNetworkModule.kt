package com.example.cookbook.di.mainflowcomponent

import com.example.cookbook.BuildConfig
import com.example.cookbook.data.network.RecipesNetworkService
import com.example.cookbook.data.network.TokenInterceptor
import com.example.cookbook.data.network.UserHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RecipeNetworkModule {

    @LoggedUserScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @LoggedUserScope
    @Provides
    fun provideService(retrofit: Retrofit): RecipesNetworkService {
        return retrofit.create(RecipesNetworkService::class.java)
    }

    @LoggedUserScope
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @LoggedUserScope
    @Provides
    fun provideInterceptors(
        tokenInterceptor: TokenInterceptor,
        headerInterceptor: UserHeaderInterceptor
    ): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()

        interceptors.add(tokenInterceptor)

        interceptors.add(headerInterceptor)

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        interceptors.add(loggingInterceptor)
        return interceptors
    }

}
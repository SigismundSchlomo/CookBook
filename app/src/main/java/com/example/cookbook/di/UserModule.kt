package com.example.cookbook.di

import com.example.cookbook.data.UserRepositoryImpl
import com.example.cookbook.domain.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @Binds
    abstract fun provideUserRepository(repo: UserRepositoryImpl): UserRepository

}
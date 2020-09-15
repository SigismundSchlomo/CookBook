package com.example.cookbook.di

import com.example.cookbook.data.RecipeRepositoryImpl
import com.example.cookbook.domain.RecipesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RecipesModule {

    @Binds
    @LoggedUserScope
    abstract fun provideRepository(repo: RecipeRepositoryImpl): RecipesRepository

}
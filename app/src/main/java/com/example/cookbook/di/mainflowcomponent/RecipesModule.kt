package com.example.cookbook.di.mainflowcomponent

import com.example.cookbook.data.RecipeRepositoryImpl
import com.example.cookbook.domain.RecipeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RecipesModule {

    @Binds
    @LoggedUserScope
    abstract fun provideRepository(repo: RecipeRepositoryImpl): RecipeRepository

}
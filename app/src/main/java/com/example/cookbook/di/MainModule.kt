package com.example.cookbook.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.data.RecipeRepositoryImpl
import com.example.cookbook.domain.RecipesRepository
import com.example.cookbook.presentation.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    abstract fun provideRepository(repo: RecipeRepositoryImpl): RecipesRepository

    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun recipeViewModel(viewModel: RecipeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
package com.example.cookbook.di.mainflowcomponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookbook.di.ViewModelFactory
import com.example.cookbook.di.ViewModelKey
import com.example.cookbook.presentation.mainflow.createrecipe.CreateRecipeViewModel
import com.example.cookbook.presentation.mainflow.recipeslist.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun recipeViewModel(viewModel: RecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateRecipeViewModel::class)
    abstract fun createRecipeViewModel(viewModel: CreateRecipeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
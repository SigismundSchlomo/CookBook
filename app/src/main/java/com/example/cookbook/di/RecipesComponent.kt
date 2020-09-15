package com.example.cookbook.di

import android.content.Context
import com.example.cookbook.presentation.CreateFragment
import com.example.cookbook.presentation.ListFragment
import dagger.BindsInstance
import dagger.Component

@LoggedUserScope
@Component(
    modules = [
        MainModule::class,
        RecipeNetworkModule::class,
        DatabaseModule::class,
        ConnectivityModule::class,
        RecipesModule::class
    ]
)
interface RecipesComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): RecipesComponent
    }

    fun inject(fragment: ListFragment)
    fun inject(fragment: CreateFragment)

}
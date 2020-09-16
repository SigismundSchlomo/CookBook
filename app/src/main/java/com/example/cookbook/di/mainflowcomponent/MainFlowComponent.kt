package com.example.cookbook.di.mainflowcomponent

import android.content.Context
import com.example.cookbook.di.ConnectivityModule
import com.example.cookbook.presentation.mainflow.CreateRecipeFragment
import com.example.cookbook.presentation.mainflow.ListFragment
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
interface MainFlowComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance token: String
        ): MainFlowComponent
    }

    fun inject(fragment: ListFragment)
    fun inject(fragment: CreateRecipeFragment)

}
package com.example.cookbook.di.mainflowcomponent

import android.content.Context
import com.example.cookbook.di.ConnectivityModule
import com.example.cookbook.di.UserModule
import com.example.cookbook.presentation.mainflow.recipe.CreateRecipeFragment
import com.example.cookbook.presentation.mainflow.recipeslist.RecipeListFragment
import dagger.BindsInstance
import dagger.Component

@LoggedUserScope
@Component(
    modules = [
        UserModule::class,
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
            @BindsInstance context: Context
        ): MainFlowComponent
    }

    fun inject(fragment: RecipeListFragment)
    fun inject(fragment: CreateRecipeFragment)

}
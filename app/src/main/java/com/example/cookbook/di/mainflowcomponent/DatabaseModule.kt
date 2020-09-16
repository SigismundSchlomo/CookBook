package com.example.cookbook.di.mainflowcomponent

import android.content.Context
import androidx.room.Room
import com.example.cookbook.data.db.RecipeDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @LoggedUserScope
    @Provides
    fun provideRecipeDatabase(context: Context): RecipeDatabase {
        return Room.databaseBuilder(context, RecipeDatabase::class.java, "recipeDb")
            .build()
    }

    @LoggedUserScope
    @Provides
    fun provideDao(database: RecipeDatabase) = database.recipeDao()

}
package com.example.cookbook.di

import android.content.Context
import androidx.room.Room
import com.example.cookbook.data.db.RecipeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: Context): RecipeDatabase {
        return Room.databaseBuilder(context, RecipeDatabase::class.java, "recipeDb")
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: RecipeDatabase) = database.recipeDao()

}
package com.example.cookbook.di

import android.content.Context
import com.example.cookbook.presentation.CreateFragment
import com.example.cookbook.presentation.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        MainModule::class,
        ConnectivityModule::class,
        DatabaseModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ListFragment)
    fun inject(fragment: CreateFragment)

}
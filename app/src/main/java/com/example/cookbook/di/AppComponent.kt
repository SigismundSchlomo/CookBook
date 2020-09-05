package com.example.cookbook.di

import com.example.cookbook.presentation.CreateFragment
import com.example.cookbook.presentation.ListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MainModule::class])
interface AppComponent {

    fun inject(fragment: ListFragment)
    fun inject(fragment: CreateFragment)

}
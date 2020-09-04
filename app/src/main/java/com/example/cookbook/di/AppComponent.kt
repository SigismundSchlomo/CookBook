package com.example.cookbook.di

import com.example.cookbook.presentation.ListFragment
import com.example.cookbook.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MainModule::class, StateMachinesModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ListFragment)

}
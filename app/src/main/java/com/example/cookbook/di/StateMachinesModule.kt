package com.example.cookbook.di

import com.example.cookbook.presentation.MainActivityStateMachine
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StateMachinesModule {

    @Provides
    @Singleton
    fun provideMainActivityStateMachine(): MainActivityStateMachine {
        return MainActivityStateMachine()
    }

}
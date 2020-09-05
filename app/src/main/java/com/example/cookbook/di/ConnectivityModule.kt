package com.example.cookbook.di

import com.example.cookbook.utils.ConnectivityManager
import com.example.cookbook.utils.ConnectivityManagerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ConnectivityModule {

    @Binds
    abstract fun provideConnectivityManager(manager: ConnectivityManagerImpl): ConnectivityManager

}
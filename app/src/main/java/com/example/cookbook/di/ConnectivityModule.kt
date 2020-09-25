package com.example.cookbook.di

import com.example.cookbook.utils.ConnectivityManagerWrapper
import com.example.cookbook.utils.ConnectivityManagerWrapperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ConnectivityModule {

    @Binds
    abstract fun provideConnectivityManager(manager: ConnectivityManagerWrapperImpl): ConnectivityManagerWrapper

}
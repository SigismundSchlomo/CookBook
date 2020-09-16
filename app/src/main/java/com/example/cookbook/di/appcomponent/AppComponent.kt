package com.example.cookbook.di.appcomponent

import android.content.Context
import com.example.cookbook.di.ConnectivityModule
import com.example.cookbook.presentation.SplashActivity
import com.example.cookbook.presentation.authflow.CreateAccountFragment
import com.example.cookbook.presentation.authflow.LoginFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthNetworkModule::class,
        ConnectivityModule::class,
        AuthModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: LoginFragment)
    fun inject(fragment: CreateAccountFragment)
    fun inject(activity: SplashActivity)

}
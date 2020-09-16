package com.example.cookbook

import android.app.Application
import com.example.cookbook.di.appcomponent.AppComponent
import com.example.cookbook.di.appcomponent.DaggerAppComponent
import timber.log.Timber

open class App() : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
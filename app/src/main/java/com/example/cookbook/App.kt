package com.example.cookbook

import android.app.Application
import com.example.cookbook.di.AppComponent
import com.example.cookbook.di.DaggerAppComponent
import timber.log.Timber

open class App() : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
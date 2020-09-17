package com.example.cookbook.di.appcomponent

import androidx.lifecycle.ViewModel
import com.example.cookbook.di.ViewModelKey
import com.example.cookbook.presentation.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun authViewModel(viewModel: SplashViewModel): ViewModel

}
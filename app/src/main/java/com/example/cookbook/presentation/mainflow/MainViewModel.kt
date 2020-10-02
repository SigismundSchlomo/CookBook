package com.example.cookbook.presentation.mainflow

import androidx.lifecycle.ViewModel
import com.example.cookbook.domain.usecases.RecipeInteractor
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: RecipeInteractor
) : ViewModel() {

    fun logout() {
        interactor.logoutUser()
        Timber.d("Successfully logout")
    }

}
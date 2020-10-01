package com.example.cookbook.presentation.mainflow.createrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.domain.usecases.RecipeInteractor
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CreateRecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor
) : ViewModel() {

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun createRecipe(header: String, body: String) {
        viewModelScope.launch {
            try {
                interactor.createRecipe(header, body)
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNABLE_TO_CREATE
            }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            try {
                interactor.deleteRecipe(recipe)
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNABLE_TO_DELETE
            }
        }
    }


    //TODO: Expand functionality to update recipes

    enum class ErrorMessage {
        UNABLE_TO_CREATE,
        UNABLE_TO_DELETE
    }

}
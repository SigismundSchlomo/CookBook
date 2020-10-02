package com.example.cookbook.presentation.mainflow.recipeslist

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.domain.usecases.RecipeInteractor
import com.example.cookbook.presentation.ErrorMessage
import com.example.cookbook.utils.ConnectivityManagerWrapper
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val connectivityManagerWrapper: ConnectivityManagerWrapper,
    private val interactor: RecipeInteractor
) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesLiveData: LiveData<List<Recipe>>
        get() = _recipesLiveData

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun refreshRecipes() {
        loadFromNetwork()
    }

    fun loadRecipes() {
        if (connectivityManagerWrapper.isConnected()) {
            loadFromNetwork()
        } else {
            getFromDatabase()
        }
    }

    //Delete after refactoring
    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            try {
                interactor.deleteRecipe(recipe)
            } catch (t: Throwable) {
                Timber.d(t)

            }
        }
    }


    private fun loadFromNetwork() {
        viewModelScope.launch {
            try {
                _recipesLiveData.value = interactor.loadRecipesFromNetwork()
            } catch (ne: NetworkErrorException) {
                Timber.d(ne)
                _errorMessage.value = when (ne.message) {
                    "HTTP 503 Service Unavailable" -> ErrorMessage.SERVICE_UNAVAILABLE
                    else -> ErrorMessage.UNKNOWN_ERROR
                }
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNKNOWN_ERROR
            }
        }
    }

    private fun getFromDatabase() {
        viewModelScope.launch {
            _recipesLiveData.value = interactor.loadRecipesFromDatabase()
            _errorMessage.value = ErrorMessage.DATA_FROM_DATABASE
        }
    }


}
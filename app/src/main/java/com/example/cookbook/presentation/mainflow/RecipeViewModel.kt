package com.example.cookbook.presentation.mainflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.RecipesRepository
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.presentation.ErrorMessage
import com.example.cookbook.utils.ConnectivityManagerWrapper
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val repo: RecipesRepository,
    private val connectivityManagerWrapper: ConnectivityManagerWrapper
) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesLiveData: LiveData<List<Recipe>>
        get() = _recipesLiveData

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun refreshRecipes() {
        if (connectivityManagerWrapper.isConnected()) {
            tryNetworkCall()
        } else {
            getFromDatabase()
        }
    }

    fun createRecipe(header: String, body: String) {
        viewModelScope.launch {
            try {
                repo.createRecipe(header, body)
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = ErrorMessage.UNKNOWN_ERROR
            }
        }
    }

    private fun tryNetworkCall() {
        viewModelScope.launch {
            try {
                _recipesLiveData.value = repo.getRecipes()
            } catch (t: Throwable) {
                Timber.d(t)
                _errorMessage.value = when (t.message) {
                    "HTTP 503 Service Unavailable" -> ErrorMessage.SERVICE_UNAVAILABLE
                    else -> ErrorMessage.UNKNOWN_ERROR
                }
            }
        }
    }

    private fun getFromDatabase() {
        viewModelScope.launch {
            _recipesLiveData.value = repo.getFromDatabase()
            _errorMessage.value = ErrorMessage.DATA_FROM_DATABASE
        }
    }

}
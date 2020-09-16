package com.example.cookbook.presentation.mainflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.Recipe
import com.example.cookbook.domain.RecipesRepository
import com.example.cookbook.utils.ConnectivityManager
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val repo: RecipesRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesLiveData: LiveData<List<Recipe>>
        get() = _recipesLiveData

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    fun refreshRecipes() {
        if (connectivityManager.isConnected()) {
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
            _errorMessage.value =
                ErrorMessage.DATA_FROM_DATABASE
        }
    }

    enum class ErrorMessage {
        SERVICE_UNAVAILABLE,
        DATA_FROM_DATABASE,
        UNKNOWN_ERROR
    }

}
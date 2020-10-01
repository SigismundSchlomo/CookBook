package com.example.cookbook.presentation.mainflow.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.models.CookingStep
import com.example.cookbook.domain.models.Ingredient
import com.example.cookbook.domain.models.Recipe
import com.example.cookbook.domain.usecases.RecipeInteractor
import com.example.cookbook.utils.forceRefresh
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CreateRecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor
) : ViewModel() {

    private val _errorMessage = MutableLiveData<ErrorMessage>()
    val errorMessage: LiveData<ErrorMessage>
        get() = _errorMessage

    private val _ingredients = MutableLiveData<MutableList<Ingredient>>(mutableListOf())
    val ingredients: LiveData<MutableList<Ingredient>>
        get() = _ingredients

    private val _cookingSteps = MutableLiveData<MutableList<CookingStep>>(mutableListOf())
    val cookingSteps: LiveData<MutableList<CookingStep>>
        get() = _cookingSteps

    fun createRecipe(header: String, body: String) {
        viewModelScope.launch {
            try {
                val recipe = Recipe(
                    header = header,
                    body = body,
                    ingredients = _ingredients.value?.toList()!!,
                    cookingSteps = _cookingSteps.value?.toList()!!
                )
                interactor.createRecipe(recipe)
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

    fun createIngredient(name: String, amount: String) {
        val userId = interactor.getCurrentUser().id
        _ingredients.value?.add(
            Ingredient(
                userId = userId,
                name = name,
                quantity = amount,
                isInTheList = false
            )
        )
        _ingredients.forceRefresh()
    }

    fun createCookingStep(description: String) {
        _cookingSteps.value?.add(CookingStep(description = description))
        _cookingSteps.forceRefresh()
    }

    //TODO: Expand functionality to update recipes

    enum class ErrorMessage {
        UNABLE_TO_CREATE,
        UNABLE_TO_DELETE
    }

}
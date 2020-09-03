package com.example.cookbook.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.domain.Recipe
import com.example.cookbook.domain.RecipesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(private val repo: RecipesRepository) : ViewModel() {

    private val _recipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesLiveData: LiveData<List<Recipe>>
        get() = _recipesLiveData

    init {
        viewModelScope.launch {
            _recipesLiveData.value = repo.getRecipes()
        }
    }

    fun refreshRecipes() {
        viewModelScope.launch {
            _recipesLiveData.value = repo.getRecipes()
        }
    }

}
package com.example.cookbook.presentation

class MainActivityStateMachine {

    var currentState: State = State.RECIPE_LIST
        private set

    enum class State {
        RECIPE_LIST,
        CREATE_RECIPE
    }

    fun navigateToCreateRecipe() {
        currentState = State.CREATE_RECIPE
    }

    fun navigateToRecipeList() {
        currentState = State.RECIPE_LIST
    }

}
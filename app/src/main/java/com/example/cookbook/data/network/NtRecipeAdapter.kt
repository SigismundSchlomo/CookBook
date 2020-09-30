package com.example.cookbook.data.network

import com.example.cookbook.data.network.models.NtCookingStep
import com.example.cookbook.data.network.models.NtIngredient
import com.example.cookbook.data.network.models.NtRecipe
import com.example.cookbook.domain.models.CookingStep
import com.example.cookbook.domain.models.Ingredient
import com.example.cookbook.domain.models.Recipe

fun NtRecipe.toRecipe(): Recipe {
    return Recipe(
        id = this.id,
        header = this.header,
        body = this.body,
        ingredients = this.ingredients.map { it.toIngredient() },
        cookingSteps = this.cookingSteps.map { it.toCookingStep() }
    )
}

fun NtIngredient.toIngredient(): Ingredient {
    return Ingredient(id, userId, recipeId, name, quantity, isInTheList)
}

fun NtCookingStep.toCookingStep(): CookingStep {
    return CookingStep(id, recipeId, description)
}

fun Recipe.toNtRecipe(): NtRecipe {
    return NtRecipe(
        id = this.id,
        header = this.header,
        body = this.body,
        ingredients = this.ingredients.map { it.toNtIngredient() },
        cookingSteps = this.cookingSteps.map { it.toNtCookingStep() }
    )
}

fun Ingredient.toNtIngredient(): NtIngredient {
    return NtIngredient(id, userId, recipeId, name, quantity, isInTheList)
}

fun CookingStep.toNtCookingStep(): NtCookingStep {
    return NtCookingStep(id, recipeId, description)
}
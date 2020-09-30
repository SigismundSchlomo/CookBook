package com.example.cookbook.data.db

import com.example.cookbook.data.db.models.DbCookingStep
import com.example.cookbook.data.db.models.DbIngredient
import com.example.cookbook.data.db.models.DbRecipe
import com.example.cookbook.data.db.models.RecipeWithReferences
import com.example.cookbook.domain.models.CookingStep
import com.example.cookbook.domain.models.Ingredient
import com.example.cookbook.domain.models.Recipe

fun RecipeWithReferences.toRecipe(): Recipe {
    return Recipe(
        id = this.recipe.recipeId,
        header = this.recipe.header,
        body = this.recipe.body,
        ingredients = this.ingredientList.map { it.toIngredient() },
        cookingSteps = this.cookingStepList.map { it.toCookingStep() }
    )

}

fun DbIngredient.toIngredient(): Ingredient {
    return Ingredient(
        id = this.ingredientId,
        userId = this.userId,
        recipeId = this.recipeId,
        name = this.name,
        quantity = this.quantity,
        isInTheList = this.isInTheList
    )
}

fun DbCookingStep.toCookingStep(): CookingStep {
    return CookingStep(
        id = this.stepId,
        recipeId = this.recipeId,
        description = this.description
    )
}

fun Recipe.toDbRecipe(): DbRecipe {
    return DbRecipe(
        recipeId = this.id,
        header = this.header,
        body = this.body
    )
}

fun Ingredient.toDbIngredient(): DbIngredient {
    return DbIngredient(
        ingredientId = this.id,
        userId = this.userId,
        recipeId = this.recipeId,
        name = this.name,
        quantity = this.quantity,
        isInTheList = this.isInTheList
    )
}

fun CookingStep.toDbCookingStep(): DbCookingStep {
    return DbCookingStep(
        stepId = this.id,
        recipeId = this.recipeId,
        description = this.description
    )
}


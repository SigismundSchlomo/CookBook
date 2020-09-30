package com.example.cookbook.data.db.models

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithReferences(
    @Embedded val recipe: DbRecipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val ingredientList: List<DbIngredient>,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val cookingStepList: List<DbCookingStep>
)
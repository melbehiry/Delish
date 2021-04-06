package com.elbehiry.shared.data.recipes.ingredients.remote

import com.elbehiry.model.IngredientItem

interface GetIngredientsDataSource {
    suspend fun getIngredients(): List<IngredientItem>
}
package com.elbehiry.shared.data.recipes.ingredients.repository

import com.elbehiry.model.IngredientItem

interface IngredientsRepository {
    suspend fun getIngredients(): List<IngredientItem>
}
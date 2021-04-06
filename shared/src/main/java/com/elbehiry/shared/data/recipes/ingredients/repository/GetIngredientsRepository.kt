package com.elbehiry.shared.data.recipes.ingredients.repository

import com.elbehiry.model.IngredientItem
import com.elbehiry.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.elbehiry.shared.data.recipes.ingredients.remote.GetIngredientsDataSource
import javax.inject.Inject

class GetIngredientsRepository @Inject constructor(
    private val getIngredientsDataSource: GetIngredientsDataSource
) : IngredientsRepository {
    override suspend fun getIngredients(): List<IngredientItem> =
        getIngredientsDataSource.getIngredients()
}

package com.elbehiry.shared.data.recipes.random.repository

import com.elbehiry.model.RecipesItem

interface RandomRecipesRepository {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ) : List<RecipesItem>
}
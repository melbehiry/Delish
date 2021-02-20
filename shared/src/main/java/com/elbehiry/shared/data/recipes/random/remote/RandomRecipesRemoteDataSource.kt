package com.elbehiry.shared.data.recipes.random.remote

import com.elbehiry.model.Recipes


interface RandomRecipesRemoteDataSource {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ) : Recipes
}
package com.elbehiry.shared.data.recipes.random.repository

import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRandomRecipesRepository(
    private val randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
) : RandomRecipesRepository {
    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<RecipesItem> =
        randomRecipesRemoteDataSource.getRandomRecipes(tags, number).recipes
}
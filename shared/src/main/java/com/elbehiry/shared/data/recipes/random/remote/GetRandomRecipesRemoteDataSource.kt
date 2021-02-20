package com.elbehiry.shared.data.recipes.random.remote

import com.elbehiry.model.Recipes
import com.elbehiry.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRandomRecipesRemoteDataSource(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RandomRecipesRemoteDataSource {
    override suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes =
        withContext(ioDispatcher) {
            api.getRandomRecipes(tags = tags, number = number)
        }
}
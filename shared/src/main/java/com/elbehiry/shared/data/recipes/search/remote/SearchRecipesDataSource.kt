package com.elbehiry.shared.data.recipes.search.remote

import com.elbehiry.model.SearchItem
import com.elbehiry.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val defaultNumber = 20
const val defaultRecipeInformation = false

class SearchRecipesDataSource(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchDataSource {
    override suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem = withContext(ioDispatcher) {
        api.searchRecipes(
            query = query,
            cuisine = cuisine,
            addRecipeInformation = defaultRecipeInformation,
            number = defaultNumber,
            offset = offset
        )
    }
}
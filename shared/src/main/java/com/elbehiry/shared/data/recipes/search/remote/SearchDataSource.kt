package com.elbehiry.shared.data.recipes.search.remote

import com.elbehiry.model.SearchItem

interface SearchDataSource {
    suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem
}
package com.elbehiry.shared.data.recipes.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.recipes.search.remote.SearchDataSource
import com.elbehiry.shared.data.recipes.search.source.SearchSource
import kotlinx.coroutines.flow.Flow

class SearchRecipesRepository(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override fun searchRecipes(
        query: String?,
        cuisine: String?
    ): Flow<PagingData<RecipesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchSource(searchDataSource, query,cuisine)
            }
        ).flow
    }
}

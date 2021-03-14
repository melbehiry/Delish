package com.elbehiry.shared.data.recipes.search.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.recipes.search.remote.SearchDataSource
import javax.inject.Inject

class SearchSource @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val query: String?,
    private val cuisine: String?
    ) : PagingSource<Int, RecipesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipesItem> {
        return try {
            val page = params.key ?: 1
            val searchItem = searchDataSource.searchRecipes(
                offset = page,
                query = query,
                cuisine = cuisine
            )
            LoadResult.Page(
                data = searchItem.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipesItem>): Int? {
        return null
    }
}
/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.delish.data.recipes.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.delish.data.recipes.remote.RecipesDataSource
import com.elbehiry.model.RecipesItem
import com.elbehiry.model.toUiModel
import javax.inject.Inject

const val initialPageIndex = 1
class SearchSource @Inject constructor(
    private val recipesDataSource: RecipesDataSource,
    private val query: String?,
    private val cuisine: String?
) : PagingSource<Int, RecipesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipesItem> {
        return try {
            val page = params.key ?: initialPageIndex
            val searchItem = recipesDataSource.searchRecipes(
                offset = page,
                query = query,
                cuisine = cuisine
            )
            LoadResult.Page(
                data = searchItem.results.map {
                    it.toUiModel() },
                prevKey = if (page == initialPageIndex) null else page - 1,
                nextKey = if (searchItem.results.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipesItem>): Int? {
        return null
    }
}

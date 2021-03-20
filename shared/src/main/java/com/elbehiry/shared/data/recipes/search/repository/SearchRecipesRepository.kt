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
                SearchSource(searchDataSource, query, cuisine)
            }
        ).flow
    }
}

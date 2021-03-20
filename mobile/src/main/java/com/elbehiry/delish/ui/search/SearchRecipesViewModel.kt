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

package com.elbehiry.delish.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.search.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchRecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    fun searchRecipes(
        searchKey: String,
        searchType: SearchType
    ): Flow<PagingData<RecipesItem>> {

        val query = if (searchType == SearchType.QUERY) searchKey else ""
        val cuisine = if (searchType == SearchType.CUISINE) searchKey else ""

        val params = SearchRecipesUseCase.Params.create(
            query,
            cuisine
        )
        return searchRecipesUseCase.execute(params)
    }
}

enum class SearchType {
    CUISINE,
    QUERY
}

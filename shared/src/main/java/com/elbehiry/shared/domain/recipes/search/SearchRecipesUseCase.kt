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

package com.elbehiry.shared.domain.recipes.search

import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.recipes.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    fun execute(params: Params): Flow<PagingData<RecipesItem>> =
        searchRepository.searchRecipes(params.query, params.cuisine)

    class Params private constructor(
        val query: String?,
        val cuisine: String?
    ) {

        companion object {
            @JvmStatic
            fun create(
                query: String? = "",
                cuisine: String? = ""
            ): Params {
                return Params(query, cuisine)
            }
        }
    }
}

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

package com.delish.data.recipes.remote

import com.delish.data.remote.DelishApi
import com.elbehiry.model.Recipe
import com.elbehiry.model.Recipes
import com.elbehiry.model.SearchItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val defaultNumber = 20
const val defaultRecipeInformation = false

class SearchRecipesDataSource(
    private val api: DelishApi
) : RecipesDataSource {

    override suspend fun getRecipeInformation(id: Int?): Recipe =
        api.getRecipeInformation(id = id)

    override suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem = api.searchRecipes(
        query = query,
        cuisine = cuisine,
        addRecipeInformation = defaultRecipeInformation,
        number = defaultNumber,
        offset = offset
    )

    override suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes = api.getRandomRecipes(tags = tags, number = number)
}

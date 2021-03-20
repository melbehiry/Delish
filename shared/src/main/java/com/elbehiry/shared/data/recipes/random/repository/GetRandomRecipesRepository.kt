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

package com.elbehiry.shared.data.recipes.random.repository

import com.elbehiry.model.Recipe
import com.elbehiry.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource

class GetRandomRecipesRepository(
    private val randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
) : RandomRecipesRepository {
    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe> =
        randomRecipesRemoteDataSource.getRandomRecipes(tags, number).recipes
}

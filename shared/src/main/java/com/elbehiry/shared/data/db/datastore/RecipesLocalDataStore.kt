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

package com.elbehiry.shared.data.db.datastore

import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataStore {
    suspend fun saveRecipe(recipesItem: RecipesItem)
    fun getRecipes(): Flow<Result<List<RecipesItem>>>
}

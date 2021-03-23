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

package com.elbehiry.shared.data.db.recipes.recipedatastore

import com.elbehiry.shared.data.db.recipes.entities.RecipeEntity
import com.elbehiry.shared.data.db.recipes.tables.RecipesTable
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.db.datastore.RecipesLocalDataStore
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RecipesDatabaseDataStore @Inject constructor(
    private val recipesTable: RecipesTable,
    private val recipeMapper: RecipeMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipesLocalDataStore {

    override suspend fun saveRecipe(recipesItem: RecipesItem) {
        recipesTable.saveRecipe(
            withContext(ioDispatcher) {
                recipeMapper.mapToDataBaseRecipe(recipesItem)
            }
        )
    }

    override fun getRecipes(): Flow<Result<List<RecipesItem>>> {
        return recipesTable.getRecipes().toListDataRecipeFlow()
    }

    private fun Flow<List<RecipeEntity>>.toListDataRecipeFlow(): Flow<Result<List<RecipesItem>>> {
        return this.map { items ->
            Result.Success(items.toDataRecipes())
        }
    }
}

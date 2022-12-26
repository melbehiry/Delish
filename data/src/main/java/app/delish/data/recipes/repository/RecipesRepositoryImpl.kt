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

package app.delish.data.recipes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.delish.data.db.recipes.entities.RecipeEntity
import app.delish.data.recipes.local.RecipesLocalDataSource
import app.delish.data.recipes.remote.RecipesDataSource
import app.delish.data.recipes.source.SearchSource
import com.elbehiry.model.Recipe
import com.elbehiry.model.RecipesItem
import com.elbehiry.model.toUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesRemoteDataSource: RecipesDataSource,
    private val recipesLocalDataSource: RecipesLocalDataSource
) : RecipesRepository {

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
                SearchSource(recipesRemoteDataSource, query, cuisine)
            }
        ).flow
    }

    override suspend fun getRecipeInformation(id: Int?): RecipesItem {
        return recipesLocalDataSource.getRecipeById(id)
            ?: recipesRemoteDataSource.getRecipeInformation(id).toUiModel()
    }

    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe> =
        recipesRemoteDataSource.getRandomRecipes(tags, number).recipes

    override fun getRecipes(): Flow<PagingData<RecipeEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory= {
                recipesLocalDataSource.getRecipes()
            }
        ).flow
    }

    override suspend fun deleteRecipe(recipeId: Int?) {
        recipesLocalDataSource.deleteRecipe(recipeId)
    }

    override suspend fun isRecipeSaved(parameters: Int?): Boolean {
        return false
    }

    override suspend fun saveRecipe(recipe: RecipesItem) {
        recipesLocalDataSource.saveRecipe(recipe)
    }
}

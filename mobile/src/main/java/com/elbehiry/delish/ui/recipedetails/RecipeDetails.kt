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

package com.elbehiry.delish.ui.recipedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elbehiry.delish.ui.widget.LoadingContent

@Composable
fun RecipeDetails(
    viewModel: RecipeDetailsViewModel,
    navController: NavController
) {
    val recipeDetails by viewModel.viewState.collectAsState()
    val isLoading: Boolean by viewModel.isloading.collectAsState()

    LoadingContent(isLoading) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            item { RecipesHeader(recipeDetails.recipe, navController) }
            item {
                RecipeOptions(recipeDetails.recipe) { recipe ->
                    viewModel.saveRecipe(recipe)
                }
            }
            item { RecipeDivider() }
            item { RecipeSummary(recipeDetails.recipe) }
            item { RecipeDivider() }
            item { RecipeTags(recipeDetails.recipe) }
            item { RecipeCaloric(recipeDetails.recipe) }
            item { RecipeDivider() }
            item { RecipeIngredientTitle() }
            items(recipeDetails.recipe.ingredientOriginalString ?: listOf()) { recipe ->
                RecipeIngredientItem(recipe)
            }
            item { RecipeDivider() }
            item { RecipeSteps(recipeDetails.recipe.step) }
            item { Spacer(modifier = Modifier.height(30.dp)) }
        }
    }
}

@Composable
private fun RecipeDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.elbehiry.delish.ui.widget.LoadingContent
import com.elbehiry.model.RecipesItem

@Composable
fun RecipeDetails(
    viewModel: RecipeDetailsViewModel,
    navController: NavController
) {
    val recipe: RecipesItem by viewModel.recipeInfo.observeAsState(RecipesItem())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)

    LoadingContent(isLoading) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            item { RecipesHeader(recipe, navController) }
            item { RecipeOptions(recipe) }
            item { RecipeDivider() }
            item { RecipeSummary(recipe) }
            item { RecipeDivider() }
            item { RecipeTags(recipe) }
            item { RecipeCaloric(recipe.nutrition) }
            item { RecipeDivider() }
            item { RecipeIngredientTitle() }
            items(recipe.extendedIngredients ?: listOf()) { recipe ->
                RecipeIngredientItem(recipe)
            }
            item { RecipeDivider() }
            item { RecipeSteps(recipe.analyzedInstructions) }
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

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

package app.delish.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.delish.details.vm.DetailsViewModel
import app.delish.details.vm.ViewEvent
import app.delish.compose.view.CircularLoading

@Composable
fun DetailsScreen(
    navController: NavController
) {
    DetailsScreen(
        viewModel = hiltViewModel(),
        navController = navController
    )
}

@Composable
internal fun DetailsScreen(
    viewModel: DetailsViewModel,
    navController: NavController
) {

    val viewState by viewModel.states.collectAsStateWithLifecycle()

    CircularLoading(
        isLoading = viewState.isLoading
    ) {
        viewState.recipe?.let { recipe ->
            LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(color = MaterialTheme.colors.background)
        ) {
            item { RecipesHeader(recipe, navController) }
            item {
                RecipeOptions(recipe) { recipe ->
                    recipe.saved = !recipe.saved
                    viewModel.processEvent(ViewEvent.ToggleBookMark(recipe))
                }
            }
            item { RecipeDivider() }
            item { RecipeSummary(recipe) }
            item { RecipeDivider() }
            item { RecipeTags(recipe) }
            item { RecipeCaloric(recipe) }
            item { RecipeDivider() }
            item { RecipeIngredientTitle() }
            items(recipe.ingredientOriginalString ?: listOf()) { recipe ->
                RecipeIngredientItem(recipe)
            }
            item { RecipeDivider() }
//            item { RecipeSteps(recipeDetails.recipe.step) }
            item { Spacer(modifier = Modifier.height(30.dp)) }
        }
        }
    }
}

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

package com.elbehiry.delish.ui.home.graph

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.elbehiry.delish.ui.bookmark.BookmarkViewModel
import com.elbehiry.delish.ui.ingredient.IngredientFullList
import com.elbehiry.delish.ui.main.MainContent
import com.elbehiry.delish.ui.main.MainViewModel

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val INGREDIENT_ROUTE = "ingredient"
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@VisibleForTesting
@Composable
fun HomeNavGraph(
    startDestination: String = MainDestinations.MAIN_ROUTE,
    onCuisineSearch: (String) -> Unit,
    onIngredientSearch: (String) -> Unit,
    onDetails: (Int) -> Unit,
) {
    val mainViewModel: MainViewModel = viewModel()
    val bookmarkViewModel: BookmarkViewModel = viewModel()
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)) {
            MainContent(
                viewModel = mainViewModel,
                bookmarkViewModel = bookmarkViewModel,
                onIngredientContent = actions.onIngredientContent,
                onCuisineSearch,
                onDetails,
                onIngredientSearch
            )
        }
        composable((MainDestinations.INGREDIENT_ROUTE)) {
            IngredientFullList(mainViewModel, onIngredientSearch)
        }
    }
}

class MainActions(
    navController: NavHostController
) {
    val onIngredientContent: () -> Unit = {
        navController.navigate(
            route = MainDestinations.INGREDIENT_ROUTE
        )
    }
}

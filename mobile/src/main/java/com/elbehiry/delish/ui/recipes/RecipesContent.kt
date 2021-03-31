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

package com.elbehiry.delish.ui.recipes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.widget.LoadingContent
import com.elbehiry.delish.ui.widget.NotificationBanner
import com.elbehiry.model.RecipesItem

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun HomeContent(
    viewModel: RecipesViewModel,
    onIngredientContent: () -> Unit,
    onCuisineSearch: (String) -> Unit,
    onDetails: (Int) -> Unit,
    onIngredientSearch: (String) -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    LoadingContent(viewState.loading) {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item { HeaderTitle() }
                item {
                    DailyInspiration(viewState.randomRecipes, onDetails) { recipe ->
                        viewModel.onBookMark(recipe)
                    }
                }
                item {
                    HomeIngredient(
                        viewState.ingredientList,
                        onIngredientContent,
                        onIngredientSearch
                    )
                }
                item { Spacer(modifier = Modifier.padding(16.dp)) }
                item { HomeCuisines(viewState.cuisinesList, onCuisineSearch) }
                item { Spacer(modifier = Modifier.padding(50.dp)) }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun NotificationItem(errorMessage: String) {
    val isOpen = remember { mutableStateOf(errorMessage.isNotEmpty()) }
    NotificationBanner(
        isOpen = isOpen.value,
        notificationMessage = errorMessage,
        backgroundColor = MaterialTheme.colors.error,
        textColor = Color.White,
        buttonText = stringResource(id = R.string.retry),
        buttonTextColor = Color.White
    ) {
        isOpen.value = false
    }
}

@Composable
fun HeaderTitle() {
    Text(
        text = stringResource(id = R.string.Daily_inspiration),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 0.dp
            )
    )
}

@Composable
fun DailyInspiration(
    recipes: List<RecipesItem>,
    onDetails: (Int) -> Unit,
    onBookMark: (RecipesItem) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 8.dp, 16.dp, 16.dp
        )
    ) {
        items(recipes) { recipe ->
            InspirationItem(recipe, onDetails = onDetails) {
                onBookMark(recipe)
            }
        }
    }
}

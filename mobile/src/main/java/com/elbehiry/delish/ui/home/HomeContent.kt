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

package com.elbehiry.delish.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.elbehiry.delish.ui.main.MainViewModel
import com.elbehiry.model.RecipesItem
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem

@Composable
fun HomeContent(
    viewModel: MainViewModel,
    onIngredientContent: () -> Unit
) {
    val recipes: List<RecipesItem> by viewModel.randomRecipes.observeAsState(listOf())
    val ingredients: List<IngredientItem> by viewModel.ingredientList.observeAsState(listOf())
    val cuisines: List<CuisineItem> by viewModel.cuisinesList.observeAsState(listOf())

    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    LoadingContent(isLoading) {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item { HeaderTitle() }
                item { DailyInspiration(recipes) }
                item { HomeIngredient(ingredients, onIngredientContent) }
                item { Spacer(modifier = Modifier.padding(16.dp)) }
                item { HomeCuisines(cuisines) }
                item { Spacer(modifier = Modifier.padding(50.dp)) }
            }
        }
    }
}

@Composable
private fun LoadingContent(
    loading: Boolean,
    content: @Composable () -> Unit
) {
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    } else {
        content()
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
fun DailyInspiration(recipes: List<RecipesItem>) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 8.dp, 16.dp, 16.dp
        )
    ) {
        items(recipes) { recipe ->
            InspirationItem(recipe)
        }
    }
}

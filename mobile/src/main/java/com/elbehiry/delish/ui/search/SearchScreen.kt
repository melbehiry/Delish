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

package com.elbehiry.delish.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.recipedetails.RecipeGradient
import com.elbehiry.delish.ui.theme.compositedOnSurface
import com.elbehiry.delish.ui.widget.SearchAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elbehiry.model.RecipesItem
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalAnimationApi
@Composable
fun SearchScreen(
    navController: NavController,
    searchKey: String,
    type: SearchType = SearchType.QUERY,
    onItemClick: (Int?) -> Unit
) {
    val searchViewModel: SearchRecipesViewModel = viewModel()
    val recipes = searchViewModel.searchRecipes(searchKey, type).collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeaderItem(navController, searchKey) {
            // TODO: Handle query search next release.
        }
        LazyColumn {
            items(recipes) { recipe ->
                SearchItem(recipe = recipe, onItemClick = onItemClick)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun SearchHeaderItem(
    navController: NavController,
    title: String,
    onSearch: (String) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    SearchAppBar(
        title = title,
        textFieldValue = textState,
        onTextChanged = { textState = it },
        onBackPressed = { navController.navigateUp() },
        searchHint = stringResource(id = R.string.search_hint),
        backgroundColor = Color(0x801F1F1F)
    ) {
        onSearch(textState.text)
    }
}

@Composable
fun SearchItem(
    recipe: RecipesItem?,
    modifier: Modifier = Modifier,
    onItemClick: (Int?) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)
            .clickable { onItemClick(recipe?.id) },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            CoilImage(
                data = recipe?.image ?: "",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.compositedOnSurface(alpha = 0.2f))
                    )
                }
            )
        }
        RecipeGradient(modifier = Modifier.fillMaxSize())
        Text(
            text = recipe?.title ?: "",
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            color = Color.White,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)

        )
    }
}

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

package com.elbehiry.delish.ui.ingredient

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.main.HomeTopBar
import com.elbehiry.delish.ui.main.MainViewModel
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import com.elbehiry.model.IngredientItem

@ExperimentalFoundationApi
@Composable
fun IngredientFullList(
    viewModel: MainViewModel,
    onIngredientSearch:(String) -> Unit) {
    val ingredients: List<IngredientItem> by viewModel.ingredientList.observeAsState(listOf())

    DelishComposeTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { HomeTopBar() }
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.ingredients_wikis),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                )
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 128.dp),
                ) {
                    items(ingredients) { ingredientItem ->
                        FullIngredientItem(ingredientItem,onIngredientSearch)
                    }
                }
            }
        }
    }
}

@Composable
fun FullIngredientItem(
    ingredientItem: IngredientItem,
    onIngredientSearch:(String) -> Unit) {
    val title = stringResource(id = ingredientItem.titleId)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = ingredientItem.background))
                .clickable(onClick = {
                    onIngredientSearch(title)
                })
        ) {
            Image(
                painter = painterResource(id = ingredientItem.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(20.dp)
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
    }
}

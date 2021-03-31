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

package com.elbehiry.delish.ui.plan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.ui.widget.DaysHeader
import com.elbehiry.delish.ui.widget.LoadingContent
import com.elbehiry.model.RecipesItem
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.widget.ToggleAddButton

@Composable
fun MealPlan(
    viewModel: MealPlanViewModel,
    onDetail: (Int) -> Unit
) {
    val state by viewModel.viewState.collectAsState()
    var select by remember { mutableStateOf(0) }

    LoadingContent(state.loading) {
        val mealItem = state.meals[select]
        Column(modifier = Modifier.fillMaxSize()) {
            DaysHeader(
                selectedTextColor = MaterialTheme.colors.secondary,
                selectedBackgroundColor = MaterialTheme.colors.secondary,
            ) {
                if (select != it && it < state.meals.size) {
                    select = it
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(mealItem?.meals ?: emptyList()) { item ->
                    MealItem(item) {
                        onDetail(it ?: 0)
                    }
                }
            }
        }
    }
}

@Composable
fun MealItem(
    recipe: RecipesItem?,
    modifier: Modifier = Modifier,
    onDetail: (Int?) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp)
            .clickable { onDetail(recipe?.id) },
        color = Color.DarkGray,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .semantics {
                    customActions = listOf(
                        CustomAccessibilityAction(
                            label = "",
                            action = { true }
                        )
                    )
                }
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe?.title ?: "",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp,
                            horizontal = 4.dp
                        )
                )
                RecipeReadTime(recipe = recipe)
            }
            ToggleAddButton(
                isAdded = false,
                modifier = Modifier.clearAndSetSemantics {}
            ) {}
        }
    }
}

@Composable
fun RecipeReadTime(
    recipe: RecipesItem?,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            val textStyle = MaterialTheme.typography.body2
            Text(
                text = "${recipe?.servings} ",
                color = Color.White,
                style = textStyle
            )
            Text(
                text = stringResource(id = R.string.servings),
                style = textStyle
            )
            Text(
                text = " - ${recipe?.readyInMinutes} ${stringResource(id = R.string.minutes)}",
                style = textStyle
            )
        }
    }
}

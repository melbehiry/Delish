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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.delish.details.R
import app.delish.view.ChipView
import com.elbehiry.model.RecipesItem

@Composable
fun RecipeTags(recipe: RecipesItem) {
    val recipeTags = createTagsList(recipe)

    LazyRow(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    ) {
        items(
            recipeTags.filter {
                it.first == true
            }
        ) { tag ->
            ChipView(
                chipMessage = tag.second,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun createTagsList(recipe: RecipesItem) = listOf(
    Pair(
        recipe.cheap,
        stringResource(id = R.string.cheap)
    ),
    Pair(
        recipe.glutenFree,
        stringResource(id = R.string.glutenFree)
    ),
    Pair(
        recipe.vegetarian,
        stringResource(id = R.string.vegetarian)
    ),
    Pair(
        recipe.vegan,
        stringResource(id = R.string.vegan)
    ),
    Pair(
        recipe.dairyFree,
        stringResource(id = R.string.dairyFree)
    ),
    Pair(
        recipe.veryHealthy,
        stringResource(id = R.string.veryHealthy)
    ),
    Pair(
        recipe.veryPopular,
        stringResource(id = R.string.veryPopular)
    ),
    Pair(
        recipe.sustainable,
        stringResource(id = R.string.sustainable)
    )
)

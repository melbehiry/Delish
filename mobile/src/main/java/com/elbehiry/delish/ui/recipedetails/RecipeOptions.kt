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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Stars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elbehiry.delish.R
import com.elbehiry.model.RecipesItem

@Composable
fun RecipeOptions(
    recipe: RecipesItem,
    onSave: (RecipesItem) -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        TextButton(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = "${recipe.aggregateLikes}",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }
        TextButton(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            onClick = { }
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Filled.Stars,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = "${recipe.spoonacularScore?.toInt()}",
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }

        TextButton(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            onClick = {
                onSave(recipe)
            }
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Filled.BookmarkBorder,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.save),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }

        TextButton(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            onClick = { }
        ) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = Icons.Filled.IosShare,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = stringResource(id = R.string.share),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

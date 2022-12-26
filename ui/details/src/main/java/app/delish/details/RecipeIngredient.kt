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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.delish.details.R

@Composable
fun RecipeIngredientTitle() {
    Text(
        text = stringResource(id = R.string.ingredients),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        color = Color.White
    )
}

@Composable
fun RecipeIngredientItem(ingredientTitle: String) {
    Text(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
        text = "✓  $ingredientTitle",
        style = MaterialTheme.typography.subtitle2,
        color = Color.White
    )
}

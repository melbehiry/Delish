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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elbehiry.delish.R
import com.elbehiry.model.RecipesItem

@Composable
fun RecipeSummary(recipe: RecipesItem) {

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                ) {
                    append("${recipe.nutrientsAmount} ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                ) {
                    append(stringResource(id = R.string.calories))
                }
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxSize()
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                ) {
                    append("${recipe.readyInMinutes} ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                ) {
                    append(stringResource(id = R.string.minutes))
                }
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .fillMaxSize()
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview
fun PreviewRecipeSummary() {
    RecipeSummary(RecipesItem())
}

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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.delish.details.R
import com.elbehiry.model.RecipesItem

@Composable
fun RecipeCaloric(recipeItem: RecipesItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .requiredSize(110.dp)
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.Gray,
                        style = Stroke(1.dp.toPx())
                    )
                }
                Text(
                    text = "${recipeItem.percentProtein ?: 0} %",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Center).padding(10.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.protein),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .requiredSize(110.dp)
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.Gray,
                        style = Stroke(1.dp.toPx())
                    )
                }
                Text(
                    text = "${recipeItem.percentCarbs} %",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Center).padding(10.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.carbs),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .requiredSize(110.dp)
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.Gray,
                        style = Stroke(1.dp.toPx())
                    )
                }
                Text(
                    text = "${recipeItem.percentFat} %",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Center).padding(10.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.fat),
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp
            )
        }
    }
}

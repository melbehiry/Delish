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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.theme.compositedOnSurface
import com.elbehiry.delish.ui.widget.NetworkImage
import com.elbehiry.model.CuisineItem

@Composable
fun HomeCuisines(
    cuisines: List<CuisineItem>,
    onCuisineSearch: (String) -> Unit
) {
    if (cuisines.isNotEmpty()) {
        Column(
            modifier = Modifier.background(color = Color.DarkGray)
        ) {
            Text(
                text = stringResource(id = R.string.cuisines_you_looking),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            CuisinesList(cuisines, onCuisineSearch)
        }
    }
}

@Composable
fun CuisinesList(
    cuisines: List<CuisineItem>,
    onCuisineSearch: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 8.dp, 16.dp, 16.dp
        )
    ) {
        items(cuisines) { recipe ->
            CuisineItem(recipe, onCuisineSearch)
        }
    }
}

@Composable
fun CuisineItem(
    item: CuisineItem,
    onCuisineSearch: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .requiredSize(180.dp, 180.dp)
            .padding(8.dp)
            .clickable {
                onCuisineSearch(item.title)
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(android.graphics.Color.parseColor(item.color)))
        ) {
            NetworkImage(
                url = item.image,
                modifier = Modifier
                    .padding(8.dp)
                    .requiredSize(100.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(
                            MaterialTheme.colors.compositedOnSurface(alpha = 0.2f)
                        )
                ) {
                    CircularProgressIndicator()
                }
            }
            Text(
                text = item.title,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

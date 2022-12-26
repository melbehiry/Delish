package app.delish.discover.ui
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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.delish.discover.R
import app.delish.compose.ui.AsyncImage
import app.delish.compose.ui.VerticalGrid
import com.elbehiry.model.IngredientItem

@Composable
fun HomeIngredient(
    ingredients: List<IngredientItem>,
    onIngredientContent: () -> Unit,
    onIngredientSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier.background(color = Color.DarkGray)
    ) {
        Text(
            text = stringResource(id = R.string.search_ingredient_home),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        VerticalGrid(columns = 4) {
            ingredients.forEach { ingredientItem ->
                HomeIngredientItem(ingredientItem, onIngredientSearch)
            }
        }
        Button(
            onClick = { onIngredientContent() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 28.dp, horizontal = 16.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = stringResource(id = R.string.view_ingredients),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
        }
    }
}

@Composable
fun HomeIngredientItem(
    ingredientItem: IngredientItem,
    onIngredientSearch: (String) -> Unit
) {

    Column(
        modifier = Modifier.clickable {
            onIngredientSearch(ingredientItem.title)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .requiredSize(70.dp)
                .clip(CircleShape)
                .background(Color(android.graphics.Color.parseColor(ingredientItem.background)))
        ) {
            AsyncImage(
                model = ingredientItem.image,
                requestBuilder = { crossfade(true) },
                contentDescription = "Cuisine image",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .padding(8.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = ingredientItem.title,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
    }
}

@Composable
@Preview
fun PreviewIngredient() {
    HomeIngredientItem(
        IngredientItem(
            id = 1,
            image = "",
            background = "#d0efb3",
            title = "basil"
        )
    ) {}
}

@ExperimentalFoundationApi
@Composable
@Preview
fun PreviewIngredientList() {
    HomeIngredient(
        listOf(
            IngredientItem(
                id = 1,
                image = "",
                background = "#d0efb3",
                title = "basil"
            ),
            IngredientItem(
                id = 1,
                image = "",
                background = "#d0efb3",
                title = "basil"
            ),
            IngredientItem(
                id = 1,
                image = "",
                background = "#d0efb3",
                title = "basil"
            ),
            IngredientItem(
                id = 1,
                image = "",
                background = "#d0efb3",
                title = "basil"
            )
        ),
        {}
    ) {}
}

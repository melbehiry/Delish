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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import app.delish.compose.ui.AsyncImage
import app.delish.compose.ui.verticalGradient
import com.elbehiry.model.RecipesItem

@Composable
fun RecipesHeader(
    recipe: RecipesItem,
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        val (image, info, topBar, title) = createRefs()
        AsyncImage(
            model = recipe.image,
            requestBuilder = { crossfade(true) },
            contentDescription = "Cuisine image",
            modifier = Modifier
                .fillMaxWidth()
                .height(346.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = info.top,
                        bottomMargin = (-32).dp
                    )
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop
        )

        DetailsAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .constrainAs(topBar) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        ) { navController.navigateUp() }

        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            modifier = Modifier
                .constrainAs(info) {
                    linkTo(start = parent.start, end = parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(35.dp)
                }
        ) {
            Image(
                imageVector = Icons.Filled.Minimize,
                contentDescription = "arrow_down",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
        }
    }
}

@Composable
fun DetailsAppBar(modifier: Modifier, onBackPressed: () -> Unit) {
    ConstraintLayout(modifier) {
        val (back, share) = createRefs()
        RecipeGradient(modifier = Modifier.fillMaxSize())
        IconButton(
            onClick = onBackPressed,
            Modifier.constrainAs(back) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top, margin = 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        IconButton(
            onClick = { },
            Modifier.constrainAs(share) {
                end.linkTo(parent.end, margin = 8.dp)
                top.linkTo(parent.top, margin = 8.dp)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun RecipeGradient(modifier: Modifier) {
    Spacer(
        modifier = modifier.verticalGradient()
    )
}

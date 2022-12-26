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


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import app.delish.discover.R
import app.delish.compose.theme.DelishComposeTheme
import app.delish.compose.ui.AsyncImage
import app.delish.view.BookMarkButton
import com.elbehiry.model.RecipesItem

@Composable
fun InspirationItem(
    recipe: RecipesItem,
    modifier: Modifier = Modifier,
    onDetails: (Int) -> Unit,
    onBookMark: (RecipesItem) -> Unit
) {
    var select by remember { mutableStateOf(recipe.saved) }

    ConstraintLayout(
        modifier = Modifier
            .width(250.dp)
            .padding(8.dp)
            .clickable { onDetails(recipe.id) }
    ) {
        val (image, time, title, source) = createRefs()
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colors.background,
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = recipe.image ?: "",
                    requestBuilder = { crossfade(true) },
                    contentDescription = "Cuisine image",
                    modifier = Modifier.fillMaxSize() ,
                    contentScale = ContentScale.Crop
                )
                BookMarkButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    backgroundColor = colorResource(id = R.color.black_alpha),
                    onBookMark = {
                        recipe.saved = !recipe.saved
                        select = !select
                        onBookMark(recipe)
                    },
                    selected = select
                )
            }
        }
        Text(
            text = "${recipe.readyInMinutes?:0}MIN",
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(time) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = image.bottom,
                        bottom = title.top
                    )
                }
        )
        Text(
            text = recipe.title ?: "",
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = time.bottom,
                        bottom = source.top
                    )
                }
        )
        Text(
            text = "by ${recipe.sourceName?:""}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(source) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = title.bottom,
                        bottom = parent.bottom
                    )
                }
        )
    }
}

@Preview
@Composable
fun PreviewInspirationItem() {
    DelishComposeTheme {
        InspirationItem(RecipesItem(id=-1), onDetails = {}) {}
    }
}

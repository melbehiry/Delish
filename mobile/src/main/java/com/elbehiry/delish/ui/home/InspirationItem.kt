package com.elbehiry.delish.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import com.elbehiry.delish.ui.theme.compositedOnSurface
import com.elbehiry.delish.ui.widget.BookMarkButton
import com.elbehiry.model.RecipesItem
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun InspirationItem(
    recipe: RecipesItem,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = Modifier
        .preferredWidth(250.dp)
        .padding(8.dp)
        .clickable { }
    ) {
        val (image, time, title,source) = createRefs()
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .preferredHeight(150.dp)
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
                CoilImage(
                    data = recipe.image, contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    fadeIn = true,
                    loading = {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colors.compositedOnSurface(alpha = 0.2f))
                        )
                    }
                )
                BookMarkButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            }
        }
            Text(
                text = "${recipe.readyInMinutes}MIN",
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
            text = recipe.title,
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
            text = "by ${recipe.sourceName}",
            style = MaterialTheme.typography.subtitle2,
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
fun previewInspirationItem() {
    DelishComposeTheme {
        InspirationItem(RecipesItem(image = ""))
    }
}

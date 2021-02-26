package com.elbehiry.delish.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.theme.compositedOnSurface
import com.elbehiry.delish.ui.theme.randomBackgroundColor
import com.elbehiry.model.CuisineItem
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun HomeCuisines(cuisines: List<CuisineItem>) {
    Column(
        modifier = Modifier.background(color = Color.DarkGray)
    ) {
        Text(
            text = stringResource(id = R.string.cuisines_you_looking),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )
        CuisinesList(cuisines)
    }
}

@Composable
fun CuisinesList(cuisines: List<CuisineItem>) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 8.dp, 16.dp, 16.dp
        )
    ) {
        val cuisinesItems =
            items(cuisines) { recipe ->
                CuisineItem(recipe)
            }
    }
}

@Composable
fun CuisineItem(item: CuisineItem) {
    Surface(
        modifier = Modifier
            .requiredSize(180.dp, 180.dp)
            .padding(8.dp).clickable { },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(android.graphics.Color.parseColor(item.color)))
        ) {
            CoilImage(
                data = item.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp).requiredSize(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.compositedOnSurface(alpha = 0.2f))
                    )
                }
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6,
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

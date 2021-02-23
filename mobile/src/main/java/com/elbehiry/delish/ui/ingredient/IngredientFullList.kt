package com.elbehiry.delish.ui.ingredient

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.main.HomeTopBar
import com.elbehiry.delish.ui.main.MainViewModel
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import com.elbehiry.delish.ui.widget.VerticalGrid
import com.elbehiry.model.IngredientItem

@Composable
fun IngredientFullList(viewModel: MainViewModel) {
    val ingredients: List<IngredientItem> by viewModel.ingredientList.observeAsState(listOf())

    DelishComposeTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { HomeTopBar() }) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(id = R.string.ingredients_wikis),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                )
                VerticalGrid(columns = 2) {
                    ingredients.forEach { ingredientItem ->
                        FullIngredientItem(ingredientItem)
                    }
                }
            }
        }
    }
}

@Composable
fun FullIngredientItem(ingredientItem: IngredientItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = ingredientItem.background))
                .clickable(onClick = { })
        ) {
            Image(
                painter = painterResource(id = ingredientItem.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .preferredHeight(120.dp)
                    .preferredWidth(120.dp)
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = stringResource(id = ingredientItem.titleId),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
    }

}

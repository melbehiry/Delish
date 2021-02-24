package com.elbehiry.delish.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.util.IngredientListProvider
import com.elbehiry.delish.ui.widget.VerticalGrid
import com.elbehiry.model.IngredientItem

@Composable
fun HomeIngredient(ingredients: List<IngredientItem>, onIngredientContent: () -> Unit) {
    Column(
        modifier = Modifier.background(color = Color.DarkGray)
    ) {
        Text(
            text = stringResource(id = R.string.search_ingredient_home),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )
        VerticalGrid(columns = 4) {
            ingredients.take(8).forEach { ingredientItem ->
                HomeIngredientItem(ingredientItem)
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
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Composable
fun HomeIngredientItem(ingredientItem: IngredientItem) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .preferredSize(70.dp)
                .clip(CircleShape)
                .background(colorResource(id = ingredientItem.background))
        ) {
            Image(
                painter = painterResource(id = ingredientItem.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .preferredHeight(70.dp)
                    .preferredWidth(70.dp)
                    .padding(8.dp)
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

@Composable
@Preview
fun previewIngredient() {
    HomeIngredientItem(
        IngredientItem(
            id = 1,
            imageId = R.drawable.basil,
            background = R.color.basil_background,
            titleId = R.string.basil
        )
    )
}

@Composable
@Preview
fun previewIngredientList() {
    HomeIngredient(
        IngredientListProvider.ingredientList.take(4)
    ) {}
}

package com.elbehiry.delish.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.elbehiry.delish.ui.main.MainViewModel
import com.elbehiry.model.RecipesItem
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.model.IngredientItem


@Composable
fun HomeContent(viewModel: MainViewModel, modifier: Modifier) {
    val recipes: List<RecipesItem> by viewModel.randomRecipes.observeAsState(listOf())
    val ingredients :List<IngredientItem> by viewModel.ingredientList.observeAsState(listOf())

//    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item { HeaderTitle() }
            item { DailyInspiration(recipes) }
            item { HomeIngredient(ingredients) }
            item { Spacer(modifier = Modifier.padding(100.dp)) }
        }
    }
}

@Composable
fun HeaderTitle() {
    Text(
        text = stringResource(id = R.string.Daily_inspiration),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 0.dp
            )
    )
}

@Composable
fun DailyInspiration(recipes: List<RecipesItem>) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 8.dp, 16.dp, 16.dp
        )
    ) {
        items(recipes) { recipe ->
            InspirationItem(recipe)
        }
    }
}

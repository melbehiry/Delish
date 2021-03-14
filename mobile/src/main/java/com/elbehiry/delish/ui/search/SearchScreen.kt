package com.elbehiry.delish.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.recipedetails.RecipeGradient
import com.elbehiry.delish.ui.theme.compositedOnSurface
import com.elbehiry.delish.ui.widget.SearchAppBar
import com.elbehiry.model.RecipesItem
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalAnimationApi
@Composable
fun SearchScreen(
    searchViewModel: SearchRecipesViewModel,
    navController: NavController,
    searchKey: String,
    type : SearchType = SearchType.QUERY,
    onItemClick: (Int?) -> Unit
) {
    val recipes = searchViewModel.searchRecipes(searchKey,type).collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeaderItem(navController, searchKey){
            //TODO: Handle query search next release.
        }
        LazyColumn {
            items(recipes) { recipe ->
                SearchItem(recipe = recipe, onItemClick = onItemClick)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun SearchHeaderItem(
    navController: NavController,
    title: String,
    onSearch: (String) -> Unit) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    SearchAppBar(
        title = title,
        textFieldValue = textState,
        onTextChanged = { textState = it },
        onBackPressed = { navController.navigateUp() },
        searchHint = stringResource(id = R.string.search_hint),
        backgroundColor = Color(0x801F1F1F)
    ) {
        onSearch(textState.text)
    }
}

@Composable
fun SearchItem(
    recipe: RecipesItem?,
    modifier: Modifier = Modifier,
    onItemClick: (Int?) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)
            .clickable { onItemClick(recipe?.id) },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            CoilImage(
                data = recipe?.image ?: "",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.compositedOnSurface(alpha = 0.2f))
                    )
                }
            )
        }
        RecipeGradient(modifier = Modifier.fillMaxSize())
        Text(
            text = recipe?.title ?: "",
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            color = Color.White,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)

        )
    }
}

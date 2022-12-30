package app.delish.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.items
import app.delish.compose.ui.AsyncImage
import app.delish.compose.ui.verticalGradient
import app.delish.search.vm.SearchRecipesViewModel
import com.elbehiry.model.RecipesItem
import app.delish.compose.view.EmptyView
import app.delish.compose.view.SearchAppBar

@Composable
fun SearchScreen(
    navController: NavController,
    query: String?,
    cuisine: String?,
    onBack: () -> Unit,
    onItemClick: (Int?) -> Unit
) {
    SearchScreen(
        searchViewModel = hiltViewModel(),
        navController = navController,
        query = query,
        cuisine = cuisine,
        onBack = onBack,
        onItemClick = onItemClick
    )
}

@Composable
internal fun SearchScreen(
    searchViewModel: SearchRecipesViewModel,
    navController: NavController,
    query: String?,
    cuisine: String?,
    onBack: () -> Unit,
    onItemClick: (Int?) -> Unit
) {
    val viewState = searchViewModel.searchList.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
    ) {
        SearchHeaderItem(navController, query ?: cuisine ?: "", onBack) {}

        LazyColumn {
            items(viewState) { recipe ->
                SearchItem(recipe = recipe, onItemClick = onItemClick)
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
    }

    AnimatedVisibility(
        visible = (viewState.loadState.refresh !is LoadState.Loading
                && viewState.itemCount == 0)
    ) {
        EmptyView(
            titleText = stringResource(id = R.string.ops),
            descText = stringResource(id = R.string.no_search_result),
            imageResourceId = R.drawable.recipe_empty
        )
    }
}

@Composable
fun SearchHeaderItem(
    navController: NavController,
    title: String,
    onBack: () -> Unit,
    onSearch: (String) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    SearchAppBar(
        title = title,
        textFieldValue = textState,
        onTextChanged = { textState = it },
        onBackPressed = {
            onBack()
            navController.navigateUp()
        },
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
            AsyncImage(
                model = recipe?.image,
                requestBuilder = { crossfade(true) },
                contentDescription = "Cuisine image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 8.dp)
        )
    }
}

@Composable
fun RecipeGradient(modifier: Modifier) {
    Spacer(
        modifier = modifier.verticalGradient()
    )
}

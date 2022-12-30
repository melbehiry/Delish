package app.delish.discover.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.delish.discover.R
import app.delish.compose.view.CircularLoading
import app.delish.compose.view.EmptyView
import app.delish.discover.vm.DiscoverViewModel
import app.delish.discover.vm.ViewEvent
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import app.delish.discover.vm.ViewEffect.OpenIngredientsSheet

@Composable
fun Discover(
    onDetails: (Int) -> Unit,
    onIngredients: () -> Unit,
    bottomBarPadding: PaddingValues,
    onIngredientSearch: (String) -> Unit
) {
    Discover(
        hiltViewModel(),
        bottomBarPadding = bottomBarPadding,
        onCuisineSearch = onIngredientSearch,
        onDetails = onDetails,
        onIngredients = onIngredients,
        onIngredientSearch = onIngredientSearch
    )
}

@Composable
internal fun Discover(
    viewModel: DiscoverViewModel,
    bottomBarPadding: PaddingValues,
    onCuisineSearch: (String) -> Unit,
    onDetails: (Int) -> Unit,
    onIngredients: () -> Unit,
    onIngredientSearch: (String) -> Unit
) {

    val viewState by viewModel.states.collectAsStateWithLifecycle()

    CircularLoading(
        isLoading = viewState.isLoading
    ) {
        Surface(modifier = Modifier.fillMaxSize().padding(bottomBarPadding)) {

            LaunchedEffect(key1 = true) {
                viewModel.effects.onEach { effect ->
                    when (effect) {
                        is OpenIngredientsSheet -> {
                            onIngredients()
                        }
                    }
                }.launchIn(this)
            }
            AnimatedVisibility(visible = !viewState.hasError) {
                LazyColumn(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    item { HeaderTitle() }
                    item {
                        DailyInspiration(viewState.randomRecipes, onDetails) { recipe ->
                            viewModel.processEvent(ViewEvent.ToggleBookMark(recipe))
                        }
                    }
                    item {
                        HomeIngredient(
                            viewState.ingredientList, {
                                viewModel.processEvent(ViewEvent.OpenIngredients)
                            },
                            onIngredientSearch
                        )
                    }
                    item { Spacer(modifier = Modifier.padding(16.dp)) }
                    item { HomeCuisines(viewState.cuisinesList, onCuisineSearch) }
                    item { Spacer(modifier = Modifier.padding(50.dp)) }
                }
            }
            AnimatedVisibility(visible = viewState.hasError) {
                EmptyView(
                    titleText = stringResource(id = R.string.ops),
                    descText = stringResource(id = R.string.something_went_wrong),
                    imageResourceId = R.drawable.recipe_empty
                )
            }
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
                bottom = 8.dp
            )
    )
}

@Composable
fun DailyInspiration(
    recipes: List<RecipesItem>,
    onDetails: (Int) -> Unit,
    onBookMark: (RecipesItem) -> Unit
) {
    if (recipes.isNotEmpty()) {
        InspirationContent(
            recipes, onDetails, onBookMark
        )
    }
}

@Composable
fun InspirationContent(
    recipes: List<RecipesItem>,
    onDetails: (Int) -> Unit,
    onBookMark: (RecipesItem) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(
            8.dp, 16.dp, 16.dp
        )
    ) {
        items(recipes) { recipe ->
            InspirationItem(recipe, onDetails = onDetails) {
                onBookMark(it)
            }
        }
    }
}

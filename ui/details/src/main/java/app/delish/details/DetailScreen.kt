package app.delish.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.delish.compose.ui.ScrollableBackground
import app.delish.details.vm.DetailsViewModel
import app.delish.details.vm.ViewEvent
import app.delish.view.CircularLoading

private const val RECIPE_TITLE_ITEM_KEY = "RecipeTitleCompose"

@Composable
fun DetailScreen(
    navController: NavController
) {
    DetailScreen(
        viewModel = hiltViewModel(),
        navController = navController
    )
}

@Composable
internal fun DetailScreen(
    viewModel: DetailsViewModel,
    navController: NavController,
    scrollState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onOptionsClicked: () -> Unit = {}
) = Surface(
    modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .background(color = MaterialTheme.colors.background)
) {
    val viewState by viewModel.states.collectAsStateWithLifecycle()

    CircularLoading(
        isLoading = viewState.isLoading,
        modifier = Modifier.fillMaxSize()
    ) {
        viewState.recipe?.let { recipe ->
            ScrollableBackground(
                getScrollPositionOffset = {
                    scrollState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == RECIPE_TITLE_ITEM_KEY }
                        ?.run {
                            offset.coerceAtLeast(0) + (size / 2)
                        } ?: 0
                }
            )

            LazyColumn(state = scrollState, modifier = Modifier.fillMaxSize().padding(bottom = 90.dp)) {
                item(key = "RecipeImageCompose") {
                    RecipesHeader(recipe, navController)
                }
                item(key = RECIPE_TITLE_ITEM_KEY) {
                    DetailsTitleCompose(
                        title = recipe.title ?: "",
                        subtitle = recipe.sourceName ?: "",
                        onOptionsClicked = onOptionsClicked,
                    )
                }

                item {
                    RecipeOptions(recipe) { recipe ->
                        recipe.saved = !recipe.saved
                        viewModel.processEvent(ViewEvent.ToggleBookMark(recipe))
                    }
                }
                item { RecipeDivider() }
                item { RecipeSummary(recipe) }
                item { RecipeDivider() }
                item { RecipeTags(recipe) }
                item { RecipeCaloric(recipe) }
                item { RecipeDivider() }
                if (recipe.ingredientOriginalString?.isNotEmpty() == true) {
                    item { RecipeIngredientTitle() }
                    items(recipe.ingredientOriginalString ?: listOf()) { recipe ->
                        RecipeIngredientItem(recipe)
                    }
                    item { RecipeDivider() }
                }
                item { RecipeSteps(recipe.step) }
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }

            DetailsTopBar(
                firstVisibleItemIndex = { scrollState.firstVisibleItemIndex },
                scrollToTop = {},
                onOptionsClicked = onOptionsClicked,
                contentPadding = PaddingValues(
                    top = 16.dp + contentPadding.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            )
        }
    }
}


@Composable
fun RecipeDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

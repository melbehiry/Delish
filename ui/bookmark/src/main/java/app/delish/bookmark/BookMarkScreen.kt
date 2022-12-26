package app.delish.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.delish.bookmark.vm.BookMarkViewModel
import app.delish.bookmark.vm.ViewEvent
import app.delish.view.EmptyView

@Composable
fun BookMarkScreen(
    bottomBarPadding: PaddingValues,
    onDetails: (Int) -> Unit
) {
    BookMark(
        viewModel = hiltViewModel(),
        bottomBarPadding = bottomBarPadding,
        onDetails = onDetails
    )
}

@Composable
internal fun BookMark(
    viewModel: BookMarkViewModel,
    bottomBarPadding: PaddingValues,
    onDetails: (Int) -> Unit
) {
    val viewState = viewModel.savedList.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(bottomBarPadding)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(viewState.itemCount) { index ->
                val item = viewState[index]
                item?.let {
                    InspirationItem(item,
                        onDetails = {
                            onDetails(it)
                        }) {
                        viewModel.processEvent(ViewEvent.ToggleBookMark(it.apply {
                            saved = false
                        }))
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = (viewState.loadState.refresh !is LoadState.Loading
                && viewState.itemCount == 0)
    ) {
        EmptyView(
            titleText = stringResource(id = R.string.ops),
            descText = stringResource(id = R.string.book_mark_empty),
            imageResourceId = R.drawable.recipe_empty
        )
    }
}

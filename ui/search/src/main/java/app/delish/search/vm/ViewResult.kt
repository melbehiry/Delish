package app.delish.search.vm

import androidx.paging.PagingData
import app.delish.base.vm.MviViewModel
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal sealed interface ViewResult : MviViewModel.MviViewResult {

    object ErrorResult : ViewResult

    data class SearchResult(
        val searchResult: PagingData<RecipesItem>
    ) : ViewResult
}

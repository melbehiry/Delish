package app.delish.search.vm

import androidx.paging.PagingData
import app.delish.base.vm.MviViewModel
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class ViewState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val searchResult: PagingData<RecipesItem> = PagingData.empty()
) : MviViewModel.MviViewState

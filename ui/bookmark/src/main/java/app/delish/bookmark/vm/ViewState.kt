package app.delish.bookmark.vm

import androidx.paging.PagingData
import app.delish.base.vm.MviViewModel
import app.delish.data.db.recipes.entities.RecipeEntity
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class ViewState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val savedRecipes: PagingData<RecipeEntity> = PagingData.empty()
) : MviViewModel.MviViewState

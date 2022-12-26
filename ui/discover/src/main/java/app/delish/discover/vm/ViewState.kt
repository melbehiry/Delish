package app.delish.discover.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem

internal data class ViewState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val ingredientList: List<IngredientItem> = emptyList(),
    val cuisinesList: List<CuisineItem> = emptyList(),
    val randomRecipes: List<RecipesItem> = emptyList()
) : MviViewModel.MviViewState

package app.delish.details.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.RecipesItem

internal data class ViewState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val recipe: RecipesItem? = null
) : MviViewModel.MviViewState

package app.delish.details.vm

import androidx.lifecycle.SavedStateHandle
import app.delish.base.vm.MviViewModel
import app.delish.details.vm.ViewEvent.GetRecipe
import app.delish.details.vm.ViewEvent.ToggleBookMark
import app.delish.details.vm.ViewResult.ErrorResult
import app.delish.details.vm.ViewResult.NoOpResult
import app.delish.details.vm.ViewResult.RecipeItem
import app.delish.domain.usecases.ToggleSavedRecipeUseCase
import app.delish.domain.usecases.recipes.information.GetRecipeInformationUseCase
import app.delish.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject


const val RECIPE_ID = "recipeId"
private const val DEFAULT_RECIPE_ID = -1

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val toggleSavedRecipeUseCase: ToggleSavedRecipeUseCase,
    savedStateHandle: SavedStateHandle
) : MviViewModel<ViewEvent, ViewResult, ViewState, ViewEffect>(ViewState()) {

    private val recipeId: Int = savedStateHandle[RECIPE_ID] ?: DEFAULT_RECIPE_ID

    init {
        processEvent(GetRecipe(recipeId))
    }

    override fun Flow<ViewEvent>.toResults(): Flow<ViewResult> {
        return merge(
            filterIsInstance<GetRecipe>().toGetRecipeResult(),
            filterIsInstance<ToggleBookMark>().toToggleBookMarkResult()
        )
    }

    override fun ViewResult.reduce(state: ViewState): ViewState {
        return when (this) {
            is ErrorResult -> state.copy(isLoading = false, hasError = true)
            is RecipeItem -> state.copy(
                isLoading = false,
                hasError = false,
                recipe = recipe
            )
            else -> state
        }
    }

    private fun Flow<GetRecipe>.toGetRecipeResult(): Flow<ViewResult> {
        return mapLatest { getRecipeInformationUseCase(it.recipeId) }
            .map {
                if (it is Result.Success) {
                    RecipeItem(it.data)
                } else {
                    ErrorResult
                }
            }
    }

    private fun Flow<ToggleBookMark>.toToggleBookMarkResult(): Flow<ViewResult> {
        return mapLatest {
            toggleSavedRecipeUseCase(it.recipesItem)
            NoOpResult
        }
    }
}

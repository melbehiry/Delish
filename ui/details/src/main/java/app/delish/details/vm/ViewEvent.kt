package app.delish.details.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.RecipesItem

internal sealed interface ViewEvent : MviViewModel.MviEvent {
    data class GetRecipe(val recipeId : Int) : ViewEvent
    data class ToggleBookMark(val recipesItem: RecipesItem) : ViewEvent
}

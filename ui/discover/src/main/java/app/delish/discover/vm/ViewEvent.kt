package app.delish.discover.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.RecipesItem

internal sealed interface ViewEvent : MviViewModel.MviEvent {
    object GetHomeContent : ViewEvent
    data class ToggleBookMark(val recipesItem: RecipesItem) : ViewEvent
    object OpenIngredients : ViewEvent
}

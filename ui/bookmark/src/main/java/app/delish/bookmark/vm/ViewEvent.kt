package app.delish.bookmark.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.RecipesItem

internal sealed interface ViewEvent : MviViewModel.MviEvent {
    object GetSavedRecipes : ViewEvent
    data class ToggleBookMark(val recipesItem: RecipesItem) : ViewEvent
}

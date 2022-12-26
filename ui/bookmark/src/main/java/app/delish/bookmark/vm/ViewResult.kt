package app.delish.bookmark.vm

import androidx.paging.PagingData
import app.delish.base.vm.MviViewModel
import app.delish.data.db.recipes.entities.RecipeEntity

internal sealed interface ViewResult : MviViewModel.MviViewResult {

    object ErrorResult : ViewResult

    data class SavedRecipes(
        val savedRecipes: PagingData<RecipeEntity>
    ) : ViewResult

    object NoOpResult : ViewResult
}

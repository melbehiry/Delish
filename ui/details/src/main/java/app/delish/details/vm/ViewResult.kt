package app.delish.details.vm

import app.delish.base.vm.MviViewModel
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem

internal sealed interface ViewResult : MviViewModel.MviViewResult {

    object ErrorResult : ViewResult

    data class RecipeItem(
        val recipe: RecipesItem? = null
    ) : ViewResult

    object NoOpResult : ViewResult
}

package app.delish.domain.usecases

import app.delish.data.recipes.repository.RecipesRepository
import app.delish.domain.SuspendUseCase
import app.delish.inject.IoDispatcher
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ToggleSavedRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<RecipesItem, Unit>(dispatcher) {

    override suspend fun execute(parameters: RecipesItem) {
        if (parameters.saved){
            recipesRepository.saveRecipe(parameters)
        } else{
            recipesRepository.deleteRecipe(parameters.id)
        }
    }
}

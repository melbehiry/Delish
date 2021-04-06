package com.elbehiry.shared.domain.recipes.ingredients

import com.elbehiry.model.IngredientItem
import com.elbehiry.shared.data.recipes.ingredients.repository.IngredientsRepository
import com.elbehiry.shared.domain.UseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) : UseCase<Unit, Flow<Result<List<IngredientItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<IngredientItem>>> =
        flow {
            try {
                val ingredients = ingredientsRepository.getIngredients()
                emit(Result.Success(ingredients))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}

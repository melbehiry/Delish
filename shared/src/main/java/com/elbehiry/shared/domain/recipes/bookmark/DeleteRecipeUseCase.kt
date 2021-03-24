package com.elbehiry.shared.domain.recipes.bookmark

import com.elbehiry.shared.data.db.datastore.RecipesLocalDataStore
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Int?, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Int?) = dataStore.deleteRecipe(parameters)
}
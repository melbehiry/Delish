package com.elbehiry.shared.data.recipes.ingredients.remote

import com.elbehiry.model.IngredientItem
import com.elbehiry.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetIngredientsRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetIngredientsDataSource {

    override suspend fun getIngredients(): List<IngredientItem> =
        withContext(ioDispatcher) {
            api.getIngredients()
        }
}

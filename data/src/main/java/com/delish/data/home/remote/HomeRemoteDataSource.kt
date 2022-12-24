package com.delish.data.home.remote

import com.delish.data.remote.DelishApi
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeDataSource {

    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        withContext(ioDispatcher) {
            api.getAvailableCuisines()
        }

    override suspend fun getIngredients(): List<IngredientItem> =
        withContext(ioDispatcher) {
            api.getIngredients()
        }
}
package com.elbehiry.shared.data.recipes.cuisines.remote

import com.elbehiry.model.CuisineItem
import com.elbehiry.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCuisinesRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetCuisinesDataSource {
    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        withContext(ioDispatcher) {
            api.getAvailableCuisines()
        }
}
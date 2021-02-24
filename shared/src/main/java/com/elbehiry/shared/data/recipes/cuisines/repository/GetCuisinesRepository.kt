package com.elbehiry.shared.data.recipes.cuisines.repository

import com.elbehiry.model.CuisineItem
import com.elbehiry.shared.data.recipes.cuisines.remote.GetCuisinesDataSource
import javax.inject.Inject

class GetCuisinesRepository @Inject constructor(
    private val getCuisinesDataSource: GetCuisinesDataSource
) : CuisinesRepository {
    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        getCuisinesDataSource.getAvailableCuisines()
}
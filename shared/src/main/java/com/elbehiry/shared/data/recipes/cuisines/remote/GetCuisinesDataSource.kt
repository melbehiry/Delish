package com.elbehiry.shared.data.recipes.cuisines.remote

import com.elbehiry.model.CuisineItem

interface GetCuisinesDataSource {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
package com.elbehiry.shared.data.recipes.cuisines.repository

import com.elbehiry.model.CuisineItem

interface CuisinesRepository {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
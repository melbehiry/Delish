package com.delish.data.home.repository

import com.delish.data.home.local.HomeLocalDataSource
import com.delish.data.home.remote.HomeDataSource
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {

    override suspend fun getCuisines(): List<CuisineItem> {
        val savedCuisines = homeLocalDataSource.getCuisines()
        val cuisines = savedCuisines.ifEmpty {
            homeDataSource.getAvailableCuisines().also {
                homeLocalDataSource.saveCuisines(it)
            }
        }
        return cuisines
    }

    override suspend fun getIngredients(): List<IngredientItem> {
        val savedIngredients = homeLocalDataSource.getIngredients()
        return savedIngredients.ifEmpty {
            homeDataSource.getIngredients().also {
                homeLocalDataSource.saveIngredients(it)
            }
        }
    }

    override suspend fun saveCuisines(cuisines: List<CuisineItem>) {

    }

    override suspend fun saveIngredients(ingredients: List<IngredientItem>) {
    }
}

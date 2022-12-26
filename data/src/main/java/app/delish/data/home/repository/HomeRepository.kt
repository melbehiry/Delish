package app.delish.data.home.repository

import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem

interface HomeRepository {
    suspend fun getCuisines(): List<CuisineItem>
    suspend fun getIngredients(): List<IngredientItem>
}

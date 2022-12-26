package app.delish.data.home.local

import app.delish.data.db.recipes.dao.CuisineDao
import app.delish.data.db.recipes.dao.IngredientDao
import app.delish.data.db.recipes.dao.RecipesDao
import app.delish.data.db.recipes.mapper.toCuisine
import app.delish.data.db.recipes.mapper.toCuisineEntity
import app.delish.data.db.recipes.mapper.toIngredient
import app.delish.data.db.recipes.mapper.toIngredientEntity
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val recipesDao: RecipesDao,
    private val cuisineDao : CuisineDao,
    private val ingredientDao : IngredientDao
    ) : HomeLocalDataSource {

    override suspend fun getCuisines(): List<CuisineItem>  =
        cuisineDao.getCuisines().map { it.toCuisine() }

    override suspend fun saveCuisines(cuisines: List<CuisineItem>) {
        cuisineDao.insertCuisines(cuisines.map { it.toCuisineEntity() })
    }
    override suspend fun getIngredients(): List<IngredientItem>  =
        ingredientDao.getIngredients().map { it.toIngredient() }

    override suspend fun saveIngredients(ingredients: List<IngredientItem>) {
        ingredientDao.insertIngredients(ingredients.map { it.toIngredientEntity() })
    }
}

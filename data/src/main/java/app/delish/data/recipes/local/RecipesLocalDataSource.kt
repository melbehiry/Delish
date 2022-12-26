package app.delish.data.recipes.local

import androidx.paging.PagingSource
import app.delish.data.db.recipes.entities.RecipeEntity
import com.elbehiry.model.RecipesItem

interface RecipesLocalDataSource {
    suspend fun saveRecipe(recipesItem: RecipesItem)
    fun getRecipes(): PagingSource<Int, RecipeEntity>
    suspend fun getRecipeById(recipeId: Int?): RecipesItem?
    suspend fun deleteRecipe(recipeId: Int?)
    suspend fun isRecipeSaved(recipeId: Int?): Boolean
}

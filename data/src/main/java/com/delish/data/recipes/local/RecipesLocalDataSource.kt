package com.delish.data.recipes.local

import androidx.paging.PagingSource
import com.delish.data.db.recipes.entities.RecipeEntity
import com.elbehiry.model.RecipesItem

interface RecipesLocalDataSource {
    suspend fun saveRecipe(recipesItem: RecipesItem)
    fun getRecipes(): PagingSource<Int, RecipeEntity>
    suspend fun getRecipeById(recipeId: Int?): RecipesItem?
    suspend fun deleteRecipe(recipeId: Int?)
    suspend fun isRecipeSaved(recipeId: Int?): Boolean
}
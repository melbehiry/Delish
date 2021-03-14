package com.elbehiry.shared.data.recipes.search.repository

import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRecipes(
        query:String?,
        cuisine:String?
    ): Flow<PagingData<RecipesItem>>
}
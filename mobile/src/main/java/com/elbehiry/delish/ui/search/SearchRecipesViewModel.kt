package com.elbehiry.delish.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.search.SearchRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SearchRecipesViewModel @ViewModelInject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    
    fun searchRecipes(searchKey: String, searchType: SearchType): Flow<PagingData<RecipesItem>> {

        val query = if (searchType == SearchType.QUERY) searchKey else ""
        val cuisine = if (searchType == SearchType.CUISINE) searchKey else ""

        val params = SearchRecipesUseCase.Params.create(
            query,
            cuisine
        )
        return searchRecipesUseCase.execute(params)
    }
}

enum class SearchType {
    CUISINE,
    QUERY
}
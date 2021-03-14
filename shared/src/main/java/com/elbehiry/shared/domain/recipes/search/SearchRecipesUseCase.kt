package com.elbehiry.shared.domain.recipes.search

import androidx.paging.PagingData
import com.elbehiry.model.RecipesItem
import com.elbehiry.model.SearchItem
import com.elbehiry.shared.data.recipes.search.repository.SearchRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.domain.UseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    fun execute(params: Params): Flow<PagingData<RecipesItem>> =
        searchRepository.searchRecipes(params.query,params.cuisine)

    class Params private constructor(
        val query: String?,
        val cuisine: String?) {

        companion object {
            @JvmStatic
            fun create(
                query: String? = "",
                cuisine: String? = ""
            ): Params {
                return Params(query,cuisine)
            }
        }
    }
}

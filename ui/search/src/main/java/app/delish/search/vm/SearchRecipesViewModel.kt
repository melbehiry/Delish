package app.delish.search.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.delish.base.vm.MviViewModel
import app.delish.domain.usecases.search.SearchRecipesUseCase
import app.delish.search.vm.ViewEvent.SearchQuery
import app.delish.search.vm.ViewResult.ErrorResult
import app.delish.search.vm.ViewResult.SearchResult
import com.elbehiry.model.RecipesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val QUERY_KEY = "query"
const val CUISINE_KEY = "query"

@HiltViewModel
internal class SearchRecipesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchRecipesUseCase: SearchRecipesUseCase
) : MviViewModel<ViewEvent, ViewResult, ViewState, ViewEffect>(ViewState()) {

    private val searchQuery: String = savedStateHandle[QUERY_KEY] ?: ""
    private val cuisineQuery: String = savedStateHandle[CUISINE_KEY] ?: ""
    private val _searchList = MutableStateFlow<PagingData<RecipesItem>>(PagingData.empty())
    val searchList: StateFlow<PagingData<RecipesItem>> = _searchList

    init {
        processEvent(SearchQuery(searchQuery, cuisineQuery))
    }

    override fun Flow<ViewEvent>.toResults(): Flow<ViewResult> {
        return merge(
            filterIsInstance<SearchQuery>().toSearchResult()
        )
    }

    override fun ViewResult.reduce(state: ViewState): ViewState {
        return when (this) {
            is ErrorResult -> state.copy(hasError = true)
            is SearchResult -> state.copy(
                hasError = false,
                isLoading = false,
                searchResult = searchResult
            )
        }
    }

    private fun Flow<SearchQuery>.toSearchResult(): Flow<ViewResult> {
        return flatMapLatest { searchParams ->
            val params =
                SearchRecipesUseCase.Params.create(searchParams.query, searchParams.cuisine)
            searchRecipesUseCase(params).cachedIn(viewModelScope)
        }.onEach {
            _searchList.emit(it)
        }.map {
            SearchResult(searchResult = it)
        }
    }
}


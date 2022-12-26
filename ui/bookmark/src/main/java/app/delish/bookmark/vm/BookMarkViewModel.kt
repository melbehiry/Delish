package app.delish.bookmark.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import app.delish.base.vm.MviViewModel
import app.delish.bookmark.vm.ViewEvent.GetSavedRecipes
import app.delish.bookmark.vm.ViewEvent.ToggleBookMark
import app.delish.bookmark.vm.ViewResult.ErrorResult
import app.delish.bookmark.vm.ViewResult.NoOpResult
import app.delish.bookmark.vm.ViewResult.SavedRecipes
import app.delish.data.db.recipes.mapper.toRecipesItem
import app.delish.domain.usecases.ToggleSavedRecipeUseCase
import app.delish.domain.usecases.recipes.bookmark.GetSavedRecipesUseCase
import com.elbehiry.model.RecipesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class BookMarkViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val toggleSavedRecipeUseCase: ToggleSavedRecipeUseCase
    ) : MviViewModel<ViewEvent, ViewResult, ViewState, ViewEffect>(ViewState()) {

    private val _savedList = MutableStateFlow<PagingData<RecipesItem>>(PagingData.empty())
    val savedList: StateFlow<PagingData<RecipesItem>> = _savedList

    init {
        processEvent(GetSavedRecipes)
    }

    override fun Flow<ViewEvent>.toResults(): Flow<ViewResult> {
        return merge(
            filterIsInstance<GetSavedRecipes>().toSearchResult(),
            filterIsInstance<ToggleBookMark>().toToggleBookMarkResult()
            )
    }

    override fun ViewResult.reduce(state: ViewState): ViewState {
        return when (this) {
            is ErrorResult -> state.copy(hasError = true)
            is SavedRecipes -> state.copy(
                hasError = false,
                isLoading = false,
                savedRecipes = savedRecipes
            )

            else -> state
        }
    }

    private fun Flow<ToggleBookMark>.toToggleBookMarkResult(): Flow<ViewResult> {
        return mapLatest {
            toggleSavedRecipeUseCase(it.recipesItem)
            NoOpResult
        }
    }

    private fun Flow<GetSavedRecipes>.toSearchResult(): Flow<ViewResult> {
        return flatMapLatest {
            getSavedRecipesUseCase(Unit).cachedIn(viewModelScope)
        }.onEach {
            _savedList.emit(it.map { it.toRecipesItem() })
        }.map {
            SavedRecipes(savedRecipes = it)
        }
    }
}


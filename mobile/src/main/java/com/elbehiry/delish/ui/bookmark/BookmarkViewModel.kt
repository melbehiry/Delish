/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elbehiry.delish.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.GetSavedRecipesUseCase
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipeSuspendUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkViewState())
    val state: StateFlow<BookmarkViewState>
        get() = _state

    init {
        getSavedRecipes()
    }

    private fun getSavedRecipes() {
        viewModelScope.launch {
            getSavedRecipesUseCase(Unit).collect {
                if (it.data.isNullOrEmpty()) {
                    _state.value = BookmarkViewState(isEmpty = true)
                } else {
                    _state.value = BookmarkViewState(recipes = it.data!!)
                }
            }
        }
    }

    fun deleteRecipe(recipe: RecipesItem) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe.id)
        }
    }

    data class BookmarkViewState(
        val recipes: List<RecipesItem> = emptyList(),
        val isEmpty: Boolean = false
    )
}

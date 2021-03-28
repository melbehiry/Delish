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

package com.elbehiry.delish.ui.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.liveData
import com.elbehiry.delish.ui.recipes.RecipesViewModel
import com.elbehiry.delish.ui.util.IngredientListProvider
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.information.GetRecipeInformationSuspendUseCase
import com.elbehiry.delish.ui.recipedetails.RecipeDetailsViewModel.RecipesDetailsViewState
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationSuspendUseCase,
    private val saveRecipeUseCase: SaveRecipeSuspendUseCase
) : ViewModel() {

    val isloading = MutableStateFlow(false)
    private val _state = MutableStateFlow(RecipesDetailsViewState())
    val viewState: StateFlow<RecipesDetailsViewState>
        get() = _state

    fun getRecipeDetails(id: Int) {
        isloading.value = true
        viewModelScope.launch {
            val recipeDetail = getRecipeInformationUseCase(id)
            when (recipeDetail) {
                is Result.Error -> {
                    _state.value = RecipesDetailsViewState(hasError = true)
                }
                else -> _state.value = if (recipeDetail.data != null) {
                        RecipesDetailsViewState(recipe = recipeDetail.data!!)
                    } else {
                        RecipesDetailsViewState(isEmpty = true)
                    }
            }
            isloading.value = false
        }
    }

    fun saveRecipe(recipesItem: RecipesItem) {
        viewModelScope.launch {
            saveRecipeUseCase(recipesItem)
        }
    }

    data class RecipesDetailsViewState(
        val recipe: RecipesItem = RecipesItem(),
        val isEmpty: Boolean = false,
        val hasError: Boolean = false
    )
}

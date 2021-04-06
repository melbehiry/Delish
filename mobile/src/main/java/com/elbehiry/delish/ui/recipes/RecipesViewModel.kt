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

package com.elbehiry.delish.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.IsRecipeSavedSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.cuisines.GetAvailableCuisinesUseCase
import com.elbehiry.shared.domain.recipes.ingredients.GetIngredientsUseCase
import com.elbehiry.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val randomRecipesCount = 20

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase,
    private val saveRecipeUseCase: SaveRecipeSuspendUseCase,
    private val deleteRecipeUseCase: DeleteRecipeSuspendUseCase,
    private val isRecipeSavedUseCase: IsRecipeSavedSuspendUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase
) : ViewModel() {

    val hasError = MutableStateFlow(false)
    val loading = MutableStateFlow(false)

    private val _state = MutableStateFlow(RecipesViewState())
    val viewState: StateFlow<RecipesViewState>
        get() = _state

    init {
        getHomeContent()
    }

    fun getHomeContent() {
        viewModelScope.launch {
            combine(
                getRandomRecipesUseCase(
                    GetRandomRecipesUseCase.Params.create(
                        null,
                        randomRecipesCount
                    )
                ),
                getIngredientsUseCase(Unit),
                getAvailableCuisinesUseCase(Unit)
            ) { randomRecipes, ingredients, cuisines ->
                hasError.value = cuisines is Result.Error || ingredients is Result.Error
                RecipesViewState(
                    ingredientList = ingredients.data ?: emptyList(),
                    cuisinesList = cuisines.data ?: emptyList(),
                    randomRecipes = randomRecipes.data ?: emptyList()
                )
            }.onStart {
                loading.value = true
            }.catch {
                hasError.value = true
            }.onCompletion {
                loading.value = false
            }.collect {
                _state.value = it
            }
        }
    }

    fun onBookMark(recipesItem: RecipesItem) {
        viewModelScope.launch {
            if (isRecipeSavedUseCase(recipesItem.id).data == true) {
                deleteRecipeUseCase(recipesItem.id)
            } else {
                saveRecipeUseCase(recipesItem)
            }
        }
    }

    data class RecipesViewState(
        val ingredientList: List<IngredientItem> = emptyList(),
        val cuisinesList: List<CuisineItem> = emptyList(),
        val randomRecipes: List<RecipesItem> = emptyList()
    )
}

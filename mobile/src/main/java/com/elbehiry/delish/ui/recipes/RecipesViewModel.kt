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

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elbehiry.delish.ui.util.IngredientListProvider
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.bookmark.DeleteRecipeUseCase
import com.elbehiry.shared.domain.recipes.bookmark.IsRecipeSavedUseCase
import com.elbehiry.shared.domain.recipes.bookmark.SaveRecipeUseCase
import com.elbehiry.shared.domain.recipes.cuisines.GetAvailableCuisinesUseCase
import com.elbehiry.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

const val randomRecipesCount = 20

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val isRecipeSavedUseCase: IsRecipeSavedUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasError = MutableLiveData<String>()
    val hasError: LiveData<String> = _hasError

    private val _ingredientList = MutableLiveData<List<IngredientItem>>()
    val ingredientList: LiveData<List<IngredientItem>> = _ingredientList

    private val _cuisinesList = MutableLiveData<List<CuisineItem>>()
    val cuisinesList: LiveData<List<CuisineItem>> = _cuisinesList

    private val _randomRecipes = MutableLiveData<List<RecipesItem>>()
    val randomRecipes: LiveData<List<RecipesItem>> = _randomRecipes

    init {
        getHomeContent()
    }

    private fun getHomeContent() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                coroutineScope {
                    val ingredientListDeferred = async { IngredientListProvider.ingredientList }
                    val cuisinesListDeferred = async { getAvailableCuisinesUseCase(Unit) }
                    val randomRecipesDeferred = async {
                        getRandomRecipesUseCase(
                            GetRandomRecipesUseCase.Params.create(
                                null,
                                randomRecipesCount
                            )
                        )
                    }

                    val ingredientList = ingredientListDeferred.await()
                    val cuisinesList = cuisinesListDeferred.await()
                    val randomRecipes = randomRecipesDeferred.await()

                    if (cuisinesList is Result.Error) {
                        _hasError.postValue(cuisinesList.exception.message)
                    } else if (randomRecipes is Result.Error) {
                        _hasError.postValue(randomRecipes.exception.message)
                    }

                    _randomRecipes.postValue(randomRecipes.data ?: listOf())
                    _ingredientList.postValue(ingredientList)
                    _cuisinesList.postValue(cuisinesList.data ?: listOf())
                }
            } catch (e: Exception) {
                _hasError.postValue(e.message)
            } finally {
                _isLoading.value = false
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
}

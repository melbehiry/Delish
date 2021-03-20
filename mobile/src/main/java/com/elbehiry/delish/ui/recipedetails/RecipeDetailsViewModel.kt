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
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.bookmark.SaveRecipeUseCase
import com.elbehiry.shared.domain.recipes.information.GetRecipeInformationUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val saveRecipeUseCase: SaveRecipeUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _hasError = MutableLiveData<Exception>()
    val hasError: LiveData<Exception> = _hasError

    private val recipeParam = MutableLiveData<Int>()
    private val recipeItemInfo: LiveData<Result<RecipesItem>> =
        recipeParam.switchMap { params ->
            liveData {
                emit(getRecipeInformationUseCase(params))
            }
        }

    val recipeInfo: LiveData<RecipesItem> = recipeItemInfo.map {
        when (it) {
            is Result.Error -> {
                _hasError.value = it.exception
            }
            else -> it.data
        }
        _isLoading.value = false
        it.data ?: RecipesItem()
    }

    fun getRecipeDetails(id: Int) {
        _isLoading.value = true
        recipeParam.value = id
    }

    fun saveRecipe(recipesItem: RecipesItem) {
        viewModelScope.launch {
            saveRecipeUseCase(recipesItem)
        }
    }
}

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

package com.elbehiry.delish.ui.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.model.DayMeal
import com.elbehiry.shared.domain.plan.GetMealPlanUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    private val getMealPlanUseCase: GetMealPlanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MealPlanViewState())
    val viewState: StateFlow<MealPlanViewState>
        get() = _state

    init {
        getMealPlan()
    }

    private fun getMealPlan() {
        _state.value = MealPlanViewState(loading = true)
        viewModelScope.launch {
            val recipeDetail = getMealPlanUseCase(Unit)
            when (recipeDetail) {
                is Result.Error -> {
                    _state.value = MealPlanViewState(hasError = true)
                }
                else -> _state.value = if (!recipeDetail.data.isNullOrEmpty()) {
                    MealPlanViewState(meals = recipeDetail.data!!, isEmpty = false)
                } else {
                    MealPlanViewState(isEmpty = true)
                }
            }
            MealPlanViewState(loading = true)
        }
    }

    data class MealPlanViewState(
        val loading: Boolean = false,
        val meals: List<DayMeal?> = emptyList(),
        val isEmpty: Boolean = true,
        val hasError: Boolean = false
    )
}

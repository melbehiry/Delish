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

package com.elbehiry.shared.domain.plan

import com.elbehiry.model.DayMeal
import com.elbehiry.shared.data.plan.repository.GetMealPlanRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMealPlanUseCase @Inject constructor(
    private val getMealPlanRepository: GetMealPlanRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Unit, List<DayMeal?>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<DayMeal?> {
        val mealPlan = getMealPlanRepository.getMealsPlan().week
        return listOf(
            mealPlan?.saturday,
            mealPlan?.sunday,
            mealPlan?.monday,
            mealPlan?.tuesday,
            mealPlan?.wednesday,
            mealPlan?.thursday,
            mealPlan?.friday
        )
    }
}

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

package app.delish.data.plan.repository

import com.elbehiry.model.MealsPlan
import app.delish.data.plan.remote.MealPlanDataSource
import javax.inject.Inject

class GetMealPlanRepository @Inject constructor(
    private val mealPlanRemoteDataSource: MealPlanDataSource
) : MealPlanRepository {
    override suspend fun getMealsPlan(): MealsPlan = mealPlanRemoteDataSource.getMealsPlan()
}

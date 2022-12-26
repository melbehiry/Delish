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

package app.delish.data.plan.remote

import com.elbehiry.model.MealsPlan
import app.delish.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetMealPlanRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val spoonAcularKey: String,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MealPlanDataSource {
    override suspend fun getMealsPlan(): MealsPlan =
        withContext(ioDispatcher) {
            api.getMealsPlan(apiKey = spoonAcularKey)
        }
}

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

package com.elbehiry.shared.domain.recipes.ingredients

import com.elbehiry.model.IngredientItem
import com.elbehiry.shared.data.recipes.ingredients.repository.IngredientsRepository
import com.elbehiry.shared.domain.UseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) : UseCase<Unit, Flow<Result<List<IngredientItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<IngredientItem>>> =
        flow {
            try {
                val ingredients = ingredientsRepository.getIngredients()
                emit(Result.Success(ingredients))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}

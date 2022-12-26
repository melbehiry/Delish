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

package app.delish.domain.usecases.recipes.random

import app.delish.data.recipes.repository.RecipesRepository
import app.delish.domain.UseCase
import app.delish.result.Result
import com.elbehiry.model.RecipesItem
import com.elbehiry.model.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val DEFAULT_NUMBER = 10

class GetRandomRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) : UseCase<GetRandomRecipesUseCase.Params, Flow<Result<List<RecipesItem>>>>() {

    @Suppress("TooGenericExceptionCaught")
    override fun execute(parameters: Params): Flow<Result<List<RecipesItem>>> =
        flow {
            try {
                val randomRecipes = recipesRepository.getRandomRecipes(
                    parameters.tags, parameters.number
                ).map {
                    it.toUiModel()
                }
                emit(Result.Success(randomRecipes))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    class Params private constructor(
        val tags: String?,
        val number: Int? = DEFAULT_NUMBER

    ) {

        companion object {
            @JvmStatic
            fun create(tags: String?, number: Int?): Params {
                return Params(tags, number)
            }
        }
    }
}

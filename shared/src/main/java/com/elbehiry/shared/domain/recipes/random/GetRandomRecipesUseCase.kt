package com.elbehiry.shared.domain.recipes.random

import com.elbehiry.shared.domain.UseCase
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.recipes.random.repository.RandomRecipesRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val defaultRecipesNumber = 10

class GetRandomRecipesUseCase @Inject constructor(
    private val randomRecipesRepository: RandomRecipesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<GetRandomRecipesUseCase.Params, List<RecipesItem>>(ioDispatcher) {

    override suspend fun execute(parameters: Params): List<RecipesItem>  =
        randomRecipesRepository.getRandomRecipes(parameters.tags,parameters.number)

    class Params private constructor(
        val tags: String?,
        val number: Int? = defaultRecipesNumber

    ) {

        companion object {
            @JvmStatic
            fun create(tags: String?, number: Int?): Params {
                return Params(tags, number)
            }
        }
    }
}
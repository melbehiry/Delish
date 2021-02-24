package com.elbehiry.shared.domain.recipes.cuisines

import com.elbehiry.model.CuisineItem
import com.elbehiry.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAvailableCuisinesUseCase @Inject constructor(
    private val cuisinesRepository: CuisinesRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<CuisineItem>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<CuisineItem> =
        cuisinesRepository.getAvailableCuisines()
}
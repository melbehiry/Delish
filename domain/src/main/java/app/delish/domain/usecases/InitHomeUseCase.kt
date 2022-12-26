package app.delish.domain.usecases

import app.delish.inject.IoDispatcher
import app.delish.data.home.repository.HomeRepository
import app.delish.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class InitHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Unit, Unit>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): Unit = coroutineScope {
        val ingredientsDeferred = async { homeRepository.getIngredients() }
        val cuisinesDeferred = async { homeRepository.getCuisines() }
        ingredientsDeferred.await()
        cuisinesDeferred.await()
    }
}

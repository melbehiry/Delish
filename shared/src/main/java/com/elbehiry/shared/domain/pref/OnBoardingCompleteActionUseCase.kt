package com.elbehiry.shared.domain.pref

import com.elbehiry.shared.data.pref.PreferencesKeys
import com.elbehiry.shared.data.pref.repository.DataStoreOperations
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class OnBoardingCompleteActionUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreOperations,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Boolean, Unit>(dispatcher) {
    override suspend fun execute(parameters: Boolean) =
        dataStoreRepository.save(PreferencesKeys.onBoardingCompletedKey,true)
}
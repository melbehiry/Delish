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

package app.delish.domain.usecases

import app.delish.data.home.repository.HomeRepository
import app.delish.domain.SuspendUseCase
import app.delish.inject.IoDispatcher
import com.elbehiry.model.CuisineItem
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetAvailableCuisinesUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Unit, List<CuisineItem>>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): List<CuisineItem> =
        homeRepository.getCuisines()
}

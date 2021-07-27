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

package com.elbehiry.shared.domain.location

import com.elbehiry.model.LocationModel
import com.elbehiry.shared.data.location.repository.ILocationRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Get current user location.
 */
class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: ILocationRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, LocationModel>(ioDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<LocationModel>> {
        return locationRepository.getCurrentLocation()
    }
}

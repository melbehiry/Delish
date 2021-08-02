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

package com.elbehiry.shared.domain.restaurants

import com.elbehiry.model.VenuesItem
import com.elbehiry.shared.data.restaurants.repository.ISearchRestaurantsRepository
import com.elbehiry.shared.di.IoDispatcher
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Search for the nearby restaurant using [Params] value.
 */
class SearchRestaurantsUseCase @Inject constructor(
    private val searchRepository: ISearchRestaurantsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<SearchRestaurantsUseCase.Params, List<VenuesItem>>(ioDispatcher) {

    override fun execute(parameters: Params): Flow<Result<List<VenuesItem>>> =
        searchRepository.search(
            parameters.latLng,
            parameters.version,
            parameters.radius,
            parameters.limit
        )

    class Params private constructor(
        val latLng: String,
        val version: String,
        val radius: Int?,
        val limit: Int?
    ) {

        companion object {
            @JvmStatic
            fun create(
                latLng: String,
                version: String,
                radius: Int? = null,
                limit: Int? = null
            ): Params {
                return Params(latLng, version, radius, limit)
            }
        }
    }
}

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

package com.elbehiry.shared.usecase.searchrestaurants

import com.elbehiry.shared.domain.restaurants.CreateFoursquareVersionUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class CreateFoursquareVersionUseCaseTest {

    lateinit var usecase: CreateFoursquareVersionUseCase

    @Before
    fun setup() {
        usecase = CreateFoursquareVersionUseCase()
    }

    @Test
    fun `test format the current date to foursquare version format`() {

        val year = 1993
        val month = 12
        val day = 15

        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DAY_OF_MONTH, day)
        }
        val expectationItem = "$year$month$day"
        val result = usecase(calendar.time)

        Assert.assertEquals(expectationItem, result)
    }
}

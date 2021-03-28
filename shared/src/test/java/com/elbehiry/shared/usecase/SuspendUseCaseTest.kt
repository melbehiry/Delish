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

package com.elbehiry.shared.usecase

import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.shared.domain.SuspendUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.test_shared.runBlockingTest
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SuspendUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: CoroutineDispatcher

    private lateinit var useCase: TestSuspendUseCase

    @Before
    fun setup() {
        testDispatcher = coroutineRule.testDispatcher
        useCase = TestSuspendUseCase(testDispatcher)
    }

    @Test
    fun `running use case should return result class`() {
        var result: Result<Result<Unit>>? = null

        coroutineRule.runBlockingTest {
            result = useCase.invoke(Unit)
        }

        assert(result is Result.Success)
    }

    private inner class TestSuspendUseCase(
        dispatcher: CoroutineDispatcher
    ) : SuspendUseCase<Unit, Result<Unit>>(dispatcher) {
        override suspend fun execute(parameters: Unit): Result<Unit> {
            return Result.Success(Unit)
        }
    }
}

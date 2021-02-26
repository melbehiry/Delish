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
import com.elbehiry.shared.domain.FlowUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.test_shared.runBlockingTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlowUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var useCase: ExceptionUseCase

    @Before
    fun setup() {
        testDispatcher = coroutineRule.testDispatcher
        useCase = ExceptionUseCase(testDispatcher)
    }

    @Test
    fun `exception emits Result#Error`() {
        coroutineRule.runBlockingTest {
            val result = useCase(Unit)
            assert(result.first() is Result.Error)
        }
    }

    inner class ExceptionUseCase(dispatcher: CoroutineDispatcher) :
        FlowUseCase<Unit, Unit>(dispatcher) {
        override fun execute(parameters: Unit): Flow<Result<Unit>> = flow {
            throw Exception("Test exception")
        }
    }
}

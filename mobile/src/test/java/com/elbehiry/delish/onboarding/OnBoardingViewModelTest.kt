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

package com.elbehiry.delish.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.elbehiry.delish.ui.onboarding.OnBoardingViewModel
import com.elbehiry.shared.data.pref.repository.DataStoreOperations
import com.elbehiry.shared.domain.pref.OnBoardingCompleteActionSuspendUseCase
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
class OnBoardingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: DataStoreOperations

    private lateinit var onBoardingCompleteActionUseCase: OnBoardingCompleteActionSuspendUseCase
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        onBoardingCompleteActionUseCase = OnBoardingCompleteActionSuspendUseCase(
            repository, coroutineRule.testDispatcher
        )
        onBoardingViewModel = OnBoardingViewModel(onBoardingCompleteActionUseCase)
    }

    @Test
    fun `get started clicked should update the pref`() = coroutineRule.runBlockingTest {
        onBoardingViewModel.getStartedClick()
        onBoardingViewModel.viewState.test {
            assertThat(expectItem()).isEqualTo(true)
        }
    }
}

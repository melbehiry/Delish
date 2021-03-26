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

package com.elbehiry.delish.launcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elbehiry.delish.ui.launcher.LauncherViewModel
import com.elbehiry.shared.data.pref.PreferencesKeys
import com.elbehiry.shared.data.pref.repository.DataStoreOperations
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.runBlockingTest
import come.elbehiry.app.delish.androidtest.util.LiveDataTestUtil
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.MAIN_ACTIVITY
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.ONBOARDING
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before

class LauncherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: DataStoreOperations

    private lateinit var launcherViewModel: LauncherViewModel
    private lateinit var onBoardingCompletedUseCase: OnBoardingCompletedUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        onBoardingCompletedUseCase = OnBoardingCompletedUseCase(
            repository, coroutineRule.testDispatcher
        )

        launcherViewModel = LauncherViewModel(onBoardingCompletedUseCase)
    }

    @Test
    fun `not completed onBoarding should navigate to onBoarding`() = coroutineRule.runBlockingTest {
        coEvery { repository.read(PreferencesKeys.onBoardingCompletedKey) } returns flowOf(
            Result.Success(
                false
            )
        )
        val navigation = LiveDataTestUtil.getValue(launcherViewModel.launchDestination)
        Assert.assertEquals(ONBOARDING, navigation)
    }

    @Test
    fun `completed onBoarding should navigate to main`() = coroutineRule.runBlockingTest {
        coEvery { repository.read(PreferencesKeys.onBoardingCompletedKey) } returns flowOf(
            Result.Success(
                true
            )
        )

        val navigation = LiveDataTestUtil.getValue(launcherViewModel.launchDestination)
        Assert.assertEquals(MAIN_ACTIVITY, navigation)
    }
}

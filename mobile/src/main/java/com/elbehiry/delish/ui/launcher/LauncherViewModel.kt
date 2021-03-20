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

package com.elbehiry.delish.ui.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import com.elbehiry.shared.result.Result
import kotlinx.coroutines.flow.collect
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.MAIN_ACTIVITY
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.ONBOARDING
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    val onBoardingCompletedUseCase: OnBoardingCompletedUseCase
) : ViewModel() {
    val launchDestination: LiveData<LaunchDestination> = liveData {
        onBoardingCompletedUseCase(Unit).collect { result ->
            if (result is Result.Success) {
                if (result.data) {
                    emit(MAIN_ACTIVITY)
                } else {
                    emit(ONBOARDING)
                }
            } else {
                emit(ONBOARDING)
            }
        }
    }

    enum class LaunchDestination {
        ONBOARDING,
        MAIN_ACTIVITY
    }
}

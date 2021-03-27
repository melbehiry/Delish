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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import com.elbehiry.shared.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val onBoardingCompletedUseCase: OnBoardingCompletedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LauncherViewState())
    val state: StateFlow<LauncherViewState>
        get() = _state

    init {
        getLaunchDestination()
    }

    private fun getLaunchDestination() {
        viewModelScope.launch {
            onBoardingCompletedUseCase(Unit).collect { result ->
                if (result is Result.Success) {
                    if (result.data) {
                        _state.value = LauncherViewState(LaunchDestination.MAIN_ACTIVITY)
                    } else {
                        LauncherViewState(LaunchDestination.ON_BOARDING)
                    }
                } else {
                    LauncherViewState(LaunchDestination.ON_BOARDING)
                }
            }
        }
    }
}

enum class LaunchDestination {
    ON_BOARDING,
    MAIN_ACTIVITY
}

data class LauncherViewState(
    val launchDestination: LaunchDestination = LaunchDestination.ON_BOARDING,
)

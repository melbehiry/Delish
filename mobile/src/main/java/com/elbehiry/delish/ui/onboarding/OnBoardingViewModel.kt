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

package com.elbehiry.delish.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.delish.ui.util.OnBoardingProvider
import com.elbehiry.shared.domain.pref.OnBoardingCompleteActionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val onBoardingCompleteActionUseCase: OnBoardingCompleteActionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val viewState: StateFlow<Boolean>
        get() = _state


    fun getStartedClick() {
        viewModelScope.launch {
            onBoardingCompleteActionUseCase(true)
            _state.value = true
        }
    }

    fun getOnBoardingItemsList() = OnBoardingProvider.onBoardingDataList
}

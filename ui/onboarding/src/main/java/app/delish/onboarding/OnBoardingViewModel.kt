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

package app.delish.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.delish.domain.usecases.InitHomeUseCase
import app.delish.domain.usecases.OnBoardingCompleteActionSuspendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OnBoardingViewModel @Inject constructor(
    val onBoardingCompleteActionUseCase: OnBoardingCompleteActionSuspendUseCase,
    val initHomeUseCase: InitHomeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val viewState: StateFlow<Boolean>
        get() = _state

    init {
        viewModelScope.launch {
            initHomeUseCase(Unit)
        }
    }

    fun getStartedClick() {
        viewModelScope.launch {
            onBoardingCompleteActionUseCase(true)
            _state.value = true
        }
    }

    fun getOnBoardingItemsList() = OnBoardingProvider.onBoardingItems
}

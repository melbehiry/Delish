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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.elbehiry.delish.R
import com.elbehiry.shared.domain.pref.OnBoardingCompletedUseCase
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LauncherView(
    viewModel: LauncherViewModel,
    modifier: Modifier = Modifier,
    onLauncherComplete: (LauncherViewModel.LaunchDestination) -> Unit
) {
    val launchDestination: LauncherViewModel.LaunchDestination by
    viewModel.launchDestination.observeAsState(LauncherViewModel.LaunchDestination.ONBOARDING)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onLauncherComplete)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout(launchDestination)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_delish_logo),
            contentDescription = null
        )
    }
}

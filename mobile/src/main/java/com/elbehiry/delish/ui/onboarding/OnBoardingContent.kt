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

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Button
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.main.launchMainActivity
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@VisibleForTesting
@Composable
fun OnBoardingContent(
    viewModel: OnBoardingViewModel,
    onBoardingFinished: () -> Unit
) {
    val launchDestination: Boolean by viewModel.navigateToMainActivity.observeAsState(false)

    if (launchDestination) {
        launchMainActivity(context = LocalContext.current)
        onBoardingFinished()
    }

    Scaffold(
        topBar = { OnBoardingTopBar(viewModel) },
        backgroundColor = MaterialTheme.colors.primarySurface
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .statusBarsPadding()
        ) {

            Text(
                text = "this is the first page of onBoarding",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            val state = remember { mutableStateOf(RotationStates.Original) }

            Button(
                onClick = {
                    state.value = RotationStates.Rotated
                }
            ) {
                Text(text = "Retry")
            }

            Spacer(modifier = Modifier.height(16.dp))

            val transition = updateTransition(targetState = state.value)
            val rotation by transition.animateFloat({
                tween(durationMillis = 1000)
            }) {
                if (it == RotationStates.Original) 0f else 360f
            }

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.empty_plate),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(250.dp, 250.dp)
                        .rotate(rotation)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_health_food),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(100.dp, 100.dp)
                )
            }
        }
    }
}

@Composable
fun OnBoardingTopBar(
    viewModel: OnBoardingViewModel
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_lockup_white),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = { viewModel.getStartedClick() }
        ) {
            Text(text = "Skip")
        }
    }
}

private enum class RotationStates {
    Original,
    Rotated
}

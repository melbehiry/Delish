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
import androidx.compose.animation.fadeIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Album
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.main.launchMainActivity
import com.google.accompanist.insets.statusBarsPadding

const val currentPageAnimation = "currentPageAnimation"
const val rotateAnimation = "rotateAnimation"

@ExperimentalAnimationApi
@VisibleForTesting
@Composable
fun OnBoardingContent(
    onBoardingFinished: () -> Unit
) {
    val viewModel: OnBoardingViewModel = viewModel()
    val onBoardingItemsList = viewModel.getOnBoardingItemsList()
    val launchDestination: Boolean by viewModel.viewState.collectAsState()

    if (launchDestination) {
        launchMainActivity(context = LocalContext.current)
        onBoardingFinished()
    }

    val currentPage = remember { mutableStateOf(0) }
    val transition = updateTransition(targetState = currentPage, label = currentPageAnimation)
    val rotation by transition.animateFloat(
        {
            tween(durationMillis = 1000)
        },
        label = rotateAnimation
    ) {
        if (it.value == 0) 0f else it.value * 360f
    }

    val getStartedVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.background(Color.White).fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            color = Color.White
        ) {
            AnimatedVisibility(
                visible = getStartedVisible.value,
                enter = slideInVertically(initialOffsetY = { -40 }) + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f)
            ) {
                Button(
                    onClick = {
                        viewModel.getStartedClick()
                    },
                    modifier = Modifier
                        .padding(vertical = 32.dp, horizontal = 120.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = stringResource(id = R.string.onBoarding_start),
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }
            }
            AnimatedVisibility(
                visible = !getStartedVisible.value,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                            viewModel.getStartedClick()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.skip),
                            color = MaterialTheme.colors.background,
                            style = MaterialTheme.typography.body2
                        )
                    }
                    Row(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        onBoardingItemsList.forEachIndexed { index, _ ->
                            OnBoardingSlide(
                                selected = index == currentPage.value,
                                MaterialTheme.colors.primary,
                                Icons.Filled.Album
                            )
                        }
                    }
                    TextButton(
                        onClick = {
                            if (currentPage.value != onBoardingItemsList.size - 1) {
                                currentPage.value = currentPage.value + 1
                            }
                            if (currentPage.value != onBoardingItemsList.size - 2) {
                                getStartedVisible.value = true
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = stringResource(id = R.string.next),
                            color = MaterialTheme.colors.background,
                            style = MaterialTheme.typography.body2
                        )
                        Image(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
        }

        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .requiredHeight(550.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.ic_delish_logo),
                        modifier = Modifier
                            .padding(16.dp)
                            .width(80.dp),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_plate),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(250.dp, 250.dp)
                            .rotate(rotation)
                    )

                    Image(
                        painter = painterResource(
                            onBoardingItemsList[currentPage.value].contentImageId
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(130.dp, 130.dp)

                    )
                }

                Text(
                    text = stringResource(id = onBoardingItemsList[currentPage.value].titleId),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 30.dp, top = 16.dp, end = 30.dp
                    )
                )
                Text(
                    text = stringResource(
                        id = onBoardingItemsList[currentPage.value].DescriptionId
                    ),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 30.dp, top = 16.dp, end = 30.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun OnBoardingSlide(selected: Boolean, color: Color, icon: ImageVector) {
    Icon(
        imageVector = icon,
        modifier = Modifier
            .padding(4.dp)
            .requiredSize(12.dp),
        contentDescription = null,
        tint = if (selected) color else Color.Gray
    )
}

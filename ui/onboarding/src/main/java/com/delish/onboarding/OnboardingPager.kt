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
package com.delish.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.LottieAnimation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    onBoardingPages: List<OnboardingPage>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        count = onBoardingPages.size,
        state = pagerState,
        modifier = modifier
    ) { page ->
        OnBoardingPage(onBoardingPages[page])
    }
}

@Composable
fun OnBoardingPage(item: OnboardingPage) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (image, title, subtitle) = createRefs()
        val guideline = createGuidelineFromBottom(0.2f)
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(item.animation))
        val progress by animateLottieCompositionAsState(composition)

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .padding(56.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        top = parent.top,
                        end = parent.end,
                        bottom = title.top
                    )
                }
        )
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier
                .padding(top= 60.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(subtitle.top, 20.dp)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = item.subtitle),
            color = Color.White,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(subtitle) {
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(guideline, 32.dp)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Center
        )
    }
}

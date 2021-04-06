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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.onboarding.OnBoardingActivity
import com.elbehiry.delish.ui.onboarding.OnBoardingContent
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import com.elbehiry.delish.ui.util.OnBoardingProvider
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class OnBoardingTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<OnBoardingActivity>()

    private val activity by lazy { composeTestRule.activity }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ProvideWindowInsets {
                DelishComposeTheme {
                    OnBoardingContent {}
                }
            }
        }
    }

    @Test
    fun skipButtonIsDisplayedAndWithTextSkipTest() {
        findSkipButton().assertIsDisplayed()
        findLetsGoButton().assertDoesNotExist()
    }

    @Test
    fun nextButtonIsDisplayedAndWithTextNextTest() {
        findNextButton().assertIsDisplayed()
        findLetsGoButton().assertDoesNotExist()
    }

    @Test
    fun firstPageDescriptionTitleAndDescIsDisplayedTest() {
        val firstOnBoardingItem = OnBoardingProvider.onBoardingDataList[0]
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.titleId)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.DescriptionId)
        ).assertIsDisplayed()
        findLetsGoButton().assertDoesNotExist()
    }

    private fun findSkipButton() = composeTestRule.onNodeWithText(
        activity.getString(R.string.skip), ignoreCase = true
    )

    private fun findNextButton() = composeTestRule.onNodeWithText(
        activity.getString(R.string.next), ignoreCase = true
    )

    private fun findLetsGoButton() =
        composeTestRule.onNodeWithText(
            activity.getString(R.string.onBoarding_start), ignoreCase = true
        )
}

package com.elbehiry.delish.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
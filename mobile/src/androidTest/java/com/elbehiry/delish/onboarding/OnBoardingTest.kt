package com.elbehiry.delish.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
        findLetsGoButton().assertIsNotDisplayed()
    }

    @Test
    fun nextButtonIsDisplayedAndWithTextNextTest() {
        findNextButton().assertIsDisplayed()
        findLetsGoButton().assertIsNotDisplayed()
    }

    @Test
    fun firstPageDescriptionTitleAndDescIsDisplayedTest() {
        val firstOnBoardingItem = OnBoardingProvider.onBoardingDataList[0]
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.titleId)
        )
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.DescriptionId)
        )
    }

    @Test
    fun secondPageDescriptionTitleAndDescIsDisplayedTest() {
        findNextButton().performClick()
        val firstOnBoardingItem = OnBoardingProvider.onBoardingDataList[1]
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.titleId)
        )
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.DescriptionId)
        )
    }

    @Test
    fun thirdPageDescriptionTitleAndDescIsDisplayedTest() {
        findNextButton().performClick()
        val firstOnBoardingItem = OnBoardingProvider.onBoardingDataList[2]
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.titleId)
        )
        composeTestRule.onNodeWithText(
            activity.getString(firstOnBoardingItem.DescriptionId)
        )
        findLetsGoButton().assertIsDisplayed()
        findSkipButton().assertIsNotDisplayed()
    }

    private fun findSkipButton() = composeTestRule.onNodeWithText(activity.getString(R.string.skip))
    private fun findNextButton() = composeTestRule.onNodeWithText(activity.getString(R.string.next))
    private fun findLetsGoButton() =
        composeTestRule.onNodeWithText(activity.getString(R.string.onBoarding_start))


}
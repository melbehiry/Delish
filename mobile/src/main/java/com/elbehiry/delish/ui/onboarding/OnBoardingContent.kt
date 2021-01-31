package com.elbehiry.delish.ui.onboarding

import androidx.annotation.VisibleForTesting
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@VisibleForTesting
@Composable
fun OnBoardingContent(
    onBoardingFinished: () -> Unit
){

    Text(text = "onBoarding")
}
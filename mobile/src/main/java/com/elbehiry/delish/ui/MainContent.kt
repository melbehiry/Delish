package com.elbehiry.delish.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import com.elbehiry.delish.ui.graph.MainDestinations
import com.elbehiry.delish.ui.graph.NavGraph
import com.elbehiry.delish.ui.util.AmbientBackDispatcher

@VisibleForTesting
@Composable
fun MainContent(
    isOnBoardingCompleted: Boolean,
    backDispatcher: OnBackPressedDispatcher
) {
    Providers(AmbientBackDispatcher provides backDispatcher) {
        NavGraph(
            getStartDestination(isOnBoardingCompleted)
        )
    }
}

private fun getStartDestination(onBoardingCompleted: Boolean): String {
    return if (onBoardingCompleted) {
        MainDestinations.MAIN_ROUTE
    } else {
        MainDestinations.ONBOARDING_ROUTE
    }
}

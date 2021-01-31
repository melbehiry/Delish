package com.elbehiry.delish.ui.graph

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.elbehiry.delish.ui.onboarding.OnBoardingContent

object MainDestinations {
    const val ONBOARDING_ROUTE = "onBoarding"
    const val MAIN_ROUTE = "main"
}

@VisibleForTesting
@Composable
fun NavGraph(
    startDestination: String = MainDestinations.ONBOARDING_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(MainDestinations.ONBOARDING_ROUTE) {
            OnBoardingContent{
                actions.onHomeContent
            }
        }

        composable((MainDestinations.MAIN_ROUTE)){

        }
    }
}

class MainActions(
    navController: NavHostController
) {
    val onHomeContent: () -> Unit = {
        navController.navigate(
            route = MainDestinations.MAIN_ROUTE
        )
    }
}
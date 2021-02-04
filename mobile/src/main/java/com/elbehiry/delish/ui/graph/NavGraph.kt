package com.elbehiry.delish.ui.graph

import androidx.annotation.VisibleForTesting
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val MAIN_ROUTE = "main"
}

@VisibleForTesting
@Composable
fun NavGraph(
    startDestination: String = MainDestinations.MAIN_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)){
            Text(text = "Main",color = Color.Black)
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
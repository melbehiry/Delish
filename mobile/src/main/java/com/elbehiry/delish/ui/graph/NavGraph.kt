package com.elbehiry.delish.ui.graph

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.elbehiry.delish.ui.ingredient.IngredientFullList
import com.elbehiry.delish.ui.main.MainContent
import com.elbehiry.delish.ui.main.MainViewModel

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val INGREDIENT_ROUTE = "ingredient"

}

@VisibleForTesting
@Composable
fun NavGraph(
    viewModel: MainViewModel,
    startDestination: String = MainDestinations.MAIN_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)) {
            MainContent(
                viewModel = viewModel,
                onIngredientContent = actions.onIngredientContent
            )
        }
        composable((MainDestinations.INGREDIENT_ROUTE)) {
            IngredientFullList(viewModel)
        }
    }
}

class MainActions(
    navController: NavHostController
) {
    val onIngredientContent: () -> Unit = {
        navController.navigate(
            route = MainDestinations.INGREDIENT_ROUTE
        )
    }
}
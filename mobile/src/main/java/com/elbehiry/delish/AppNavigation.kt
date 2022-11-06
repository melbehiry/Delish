package com.elbehiry.delish

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavController
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.composable

sealed class Screen(val route: String) {
    object Discover : Screen("discover")
    object BookMark : Screen("bookmark")
    object MealPlan : Screen("meal_plan")
}

sealed class LeafScreen(private val route: String) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Discover : LeafScreen("discover")
    object BookMark : LeafScreen("bookmark")
    object MealPlan : LeafScreen("meal_plan")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Discover.route,
        enterTransition = { defaultDelishEnterTransition(initialState, targetState) },
        exitTransition = { defaultDelishExitTransition(initialState, targetState) },
        popEnterTransition = { defaultDelishPopEnterTransition() },
        popExitTransition = { defaultDelishPopExitTransition() },
        modifier = modifier
    ) {
        addDiscoverTopLevel(navController)
        addMealPlanTopLevel(navController)
        addBookMarkTopLevel(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addDiscoverTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.Discover.route,
        startDestination = LeafScreen.Discover.createRoute(Screen.Discover)
    ) {
        addDiscover(navController, Screen.Discover)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMealPlanTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.MealPlan.route,
        startDestination = LeafScreen.MealPlan.createRoute(Screen.MealPlan)
    ) {
        addMealPlan(navController, Screen.MealPlan)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addBookMarkTopLevel(
    navController: NavController
) {
    navigation(
        route = Screen.BookMark.route,
        startDestination = LeafScreen.BookMark.createRoute(Screen.BookMark)
    ) {
        addBookMark(navController, Screen.BookMark)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addDiscover(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.Discover.createRoute(root)
    ) {
        Discover()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMealPlan(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.MealPlan.createRoute(root)
    ) {
        MealPlan()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addBookMark(
    navController: NavController,
    root: Screen
) {
    composable(
        route = LeafScreen.BookMark.createRoute(root)
    ) {
        BookMark()
    }
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultDelishEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): EnterTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeIn()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultDelishExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): ExitTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    // If we're crossing nav graphs (bottom navigation graphs), we crossfade
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeOut()
    }
    // Otherwise we're in the same nav graph, we can imply a direction
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)
}

private val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultDelishPopEnterTransition(): EnterTransition {
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.End)
}

@ExperimentalAnimationApi
private fun AnimatedContentScope<*>.defaultDelishPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.End)
}

@Composable
fun Discover() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Discover", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun BookMark() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "BookMark", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun MealPlan() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "MealPlan", modifier = Modifier.align(Alignment.Center))
    }
}
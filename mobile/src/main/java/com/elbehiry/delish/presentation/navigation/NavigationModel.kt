package com.elbehiry.delish.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BlurOn
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.elbehiry.delish.R

sealed class NavigationModel(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object Today : NavigationModel(
        route = Navigate.Screen.Main.route,
        title = R.string.discover_title,
        icon = Icons.Outlined.BlurOn
    )

    object BookMark : NavigationModel(
        route = Navigate.Screen.BookMark.route,
        title = R.string.bookmark_title,
        icon = Icons.Outlined.Bookmark
    )

    object MealPlan : NavigationModel(
        route = Navigate.Screen.MealPlan.route,
        title = R.string.meal_plan,
        icon = Icons.Outlined.EventNote
    )

    object Settings : NavigationModel(
        route = Navigate.Screen.Settings.route,
        title = R.string.search_title,
        icon = Icons.Outlined.Settings
    )
}

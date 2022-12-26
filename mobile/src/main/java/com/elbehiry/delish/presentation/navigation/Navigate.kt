package com.elbehiry.delish.presentation.navigation

sealed class Navigate(val route: String) {

    sealed class BottomSheet {

        object Ingredients : Navigate("ingredients_details")
    }

    sealed class Screen {

        object OnBoardingWelcome : Navigate("onboarding_welcome_screen")

        object BookMark : Navigate("book_mark")

        object MealPlan : Navigate("meal_plan")

        object Main : Navigate("main_screen")

        object Settings : Navigate("settings_screen")

        object SearchScreen : Navigate("search_screen")

        object Details : Navigate("details")

        object Notifications : Navigate("notifications_screen")

        object Language : Navigate("language_screen")
    }
}

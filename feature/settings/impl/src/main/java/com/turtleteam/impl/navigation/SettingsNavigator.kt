package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.core_navigation.BaseNavigator

class SettingsNavigator(
    private val navController: NavController
) : BaseNavigator(navController) {

    fun navigateToAboutApp() {
        navController.navigate(aboutAppRoute) {
            launchSingleTop = true
        }
    }

    fun navigateToFeedback() {
        navController.navigate(feedbackRoute) {
            launchSingleTop = true
        }
    }
}
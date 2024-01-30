package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.core_navigation.BaseNavigator

class GroupNavigator(
    private val navController: NavController
): BaseNavigator(navController) {

    private val welcomeRoute = groupGraph

    fun navigateToWelcome() {
        navController.navigate(welcomeRoute) {
            launchSingleTop = true
        }
    }
}

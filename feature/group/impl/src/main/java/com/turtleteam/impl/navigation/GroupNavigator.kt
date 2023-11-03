package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.WelcomeNavigation

class GroupNavigator(
    welcomeNavigation: WelcomeNavigation,
    private val navController: NavController
    ) {

    private val welcomeRoute = welcomeNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(welcomeRoute) {
            launchSingleTop = true
        }
    }
}

package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.WelcomeNavigation

class GroupNavigator(
    private val navController: NavController
) {

    private val welcomeRoute = groupGraph

    fun navigateToWelcome() {
        navController.navigate(welcomeRoute) {
            launchSingleTop = true
        }
    }
}

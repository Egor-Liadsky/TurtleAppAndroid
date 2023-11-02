package com.turtleteam.impl.presentation.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.WelcomeNavigation

class WelcomeNavigator(
    welcomeNavigation: WelcomeNavigation,
    private val navController: NavController
) {

    private val baseRoute = welcomeNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(baseRoute){
            launchSingleTop = true
        }
    }
}

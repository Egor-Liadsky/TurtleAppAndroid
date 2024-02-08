package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.core_navigation.BaseNavigator

class WelcomeNavigator(
    welcomeNavigation: WelcomeNavigation,
    groupNavigation: GroupNavigation,
    private val navController: NavController
): BaseNavigator(navController) {

    private val baseRoute = welcomeNavigation.baseRoute
    private val groupRoute = groupNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate("$baseRoute/register"){
            launchSingleTop = true
        }
    }

    fun navigateToGroup() {
        navController.navigate(groupRoute){
            launchSingleTop = true
            popUpTo(0)
        }
    }
}

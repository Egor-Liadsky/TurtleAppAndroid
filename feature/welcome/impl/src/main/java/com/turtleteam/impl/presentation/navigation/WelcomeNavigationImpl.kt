package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.screen.welcome.WelcomeScreen

class WelcomeNavigationImpl : WelcomeNavigation {

    override val baseRoute: String = "welcome"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            WelcomeScreen()
        }
    }
}

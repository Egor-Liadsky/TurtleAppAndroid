package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.impl.presentation.presentation.settings.screen.SettingsScreen

class SettingsNavigationImpl: SettingsNavigation {

    override val baseRoute: String = "settings"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            SettingsScreen(modifier)
        }
    }
}

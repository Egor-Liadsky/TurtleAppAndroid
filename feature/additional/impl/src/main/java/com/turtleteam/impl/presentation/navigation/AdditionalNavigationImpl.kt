package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.impl.presentation.presentation.additional.AdditionalScreen

class AdditionalNavigationImpl: AdditionalNavigation {

    override val baseRoute: String = "additional"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            AdditionalScreen(modifier)
        }
    }
}

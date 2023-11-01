package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.impl.presentation.screen.group.GroupScreen

class GroupNavigationImpl: GroupNavigation {

    override val baseRoute: String = "group"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            GroupScreen(modifier)
        }
    }
}

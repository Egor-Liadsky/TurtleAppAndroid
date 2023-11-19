package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.impl.presentation.group.screen.GroupScreen
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class GroupNavigationImpl : GroupNavigation {

    override val baseRoute: String = "group"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val navigator = koinInject<GroupNavigator>(parameters = { parametersOf(navController) })
            val viewModel = koinViewModel<GroupViewModel>(parameters = { parametersOf(navigator) })
            GroupScreen(modifier, viewModel)
        }
    }
}

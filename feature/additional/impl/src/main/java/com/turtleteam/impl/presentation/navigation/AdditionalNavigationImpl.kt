package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.impl.presentation.presentation.additional.screen.AdditionalScreen
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel
import org.koin.androidx.compose.koinViewModel

internal val additionalGraph = "additional"

class AdditionalNavigationImpl: AdditionalNavigation {

    override val baseRoute: String = additionalGraph

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val viewModel = koinViewModel<AdditionalViewModel>()
            AdditionalScreen(modifier, viewModel, navController)
        }
    }
}

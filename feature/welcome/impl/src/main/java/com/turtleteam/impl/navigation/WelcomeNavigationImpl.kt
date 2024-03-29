package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.presentation.onBoarding.screen.OnBoardingScreen
import com.turtleteam.impl.presentation.onBoarding.viewModel.OnBoardingViewModel
import com.turtleteam.impl.presentation.register.screen.WelcomeScreen
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class WelcomeNavigationImpl : WelcomeNavigation {

    override val baseRoute: String = "welcome"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route = baseRoute,
        ) {
            val navigator =
                koinInject<WelcomeNavigator>(parameters = { parametersOf(navController) })
            val viewModel =
                koinViewModel<OnBoardingViewModel>(parameters = { parametersOf(navigator) })
            OnBoardingScreen(viewModel)
        }

        navGraphBuilder.composable(
            "$baseRoute/register",

            ) {
            val navigator =
                koinInject<WelcomeNavigator>(parameters = { parametersOf(navController) })
            val viewModel =
                koinViewModel<RegisterViewModel>(parameters = { parametersOf(navigator) })
            WelcomeScreen(viewModel)
        }
    }
}

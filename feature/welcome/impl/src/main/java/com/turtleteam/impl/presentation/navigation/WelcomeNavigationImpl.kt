package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.screen.onBoarding.screen.OnBoardingScreen
import com.turtleteam.impl.presentation.screen.onBoarding.viewModel.OnBoardingViewModel
import com.turtleteam.impl.presentation.screen.welcome.screen.WelcomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

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

        navGraphBuilder.navigation(startDestination = "$baseRoute/onBoarding", route = baseRoute) {
            composable("$baseRoute/onBoarding"){
                val navigator =
                    koinInject<WelcomeNavigator>(parameters = { parametersOf(navController) })
                val viewModel =
                    koinViewModel<OnBoardingViewModel>(parameters = { parametersOf(navigator) })
                OnBoardingScreen(viewModel)
            }
        }
    }
}

package com.turtleteam.impl.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.presentation.onBoarding.screen.OnBoardingScreen
import com.turtleteam.impl.presentation.presentation.onBoarding.viewModel.OnBoardingViewModel
import com.turtleteam.impl.presentation.register.screen.WelcomeScreen
import com.turtleteam.impl.presentation.presentation.register.viewModel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class WelcomeNavigationImpl : WelcomeNavigation {

    override val baseRoute: String = "welcome"

    private val animDuration = 500

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route = baseRoute,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration),
                )
            },
        ) {
            val navigator =
                koinInject<WelcomeNavigator>(parameters = { parametersOf(navController) })
            val viewModel =
                koinViewModel<OnBoardingViewModel>(parameters = { parametersOf(navigator) })
            OnBoardingScreen(viewModel)
        }

        navGraphBuilder.composable(
            "$baseRoute/register",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animDuration),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animDuration),
                )
            },
        ) {
            val navigator =
                koinInject<WelcomeNavigator>(parameters = { parametersOf(navController) })
            val viewModel =
                koinViewModel<RegisterViewModel>(parameters = { parametersOf(navigator) })
            WelcomeScreen(viewModel)
        }
    }
}

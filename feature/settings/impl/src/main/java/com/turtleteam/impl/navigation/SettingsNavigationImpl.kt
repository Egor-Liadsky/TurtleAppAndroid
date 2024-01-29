package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.impl.presentation.aboutApp.screen.AboutAppScreen
import com.turtleteam.impl.presentation.aboutApp.viewModel.AboutAppViewModel
import com.turtleteam.impl.presentation.feedback.screen.FeedbackScreen
import com.turtleteam.impl.presentation.feedback.viewModel.FeedbackViewModel
import com.turtleteam.impl.presentation.settings.screen.SettingsScreen
import com.turtleteam.impl.presentation.settings.viewModel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

internal val settingsGraph = "settings"
internal val menuRoute = "$settingsGraph/menuRoute"
internal val aboutAppRoute = "$menuRoute/aboutApp"
internal val feedbackRoute = "$menuRoute/feedback"

class SettingsNavigationImpl : SettingsNavigation {

    override val baseRoute: String = settingsGraph

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(startDestination = menuRoute, route = baseRoute) {
            composable(route = menuRoute) {
                val navigator =
                    koinInject<SettingsNavigator>(parameters = { parametersOf(navController) })
                val viewModel =
                    koinViewModel<SettingsViewModel>(parameters = { parametersOf(navigator) })
                SettingsScreen(modifier, viewModel, navController)
            }

            composable(route = aboutAppRoute) {
                val navigator =
                    koinInject<SettingsNavigator>(parameters = { parametersOf(navController) })
                val viewModel =
                    koinViewModel<AboutAppViewModel>(parameters = { parametersOf(navigator) })
                AboutAppScreen(viewModel = viewModel)
            }

            composable(route = feedbackRoute) {
                val navigator =
                    koinInject<SettingsNavigator>(parameters = { parametersOf(navController) })
                val viewModel =
                    koinViewModel<FeedbackViewModel>(parameters = { parametersOf(navigator) })
                FeedbackScreen(viewModel = viewModel)
            }
        }
    }
}

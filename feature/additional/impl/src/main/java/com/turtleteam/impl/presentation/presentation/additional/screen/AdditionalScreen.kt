package com.turtleteam.impl.presentation.presentation.additional.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.view.layout.ScaffoldWrapper
import com.turtleteam.impl.presentation.navigation.additionalGraph
import com.turtleteam.impl.presentation.presentation.additional.screen.layout.AdditionalLayout
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel
import org.koin.compose.koinInject

@Composable
fun AdditionalScreen(
    modifier: Modifier = Modifier,
    viewModel: AdditionalViewModel,
    navController: NavHostController
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")
    val navControllerAdditional = rememberNavController()

    val teacherFeature: TeacherNavigation = koinInject()
    val groupFeature: GroupNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    ScaffoldWrapper(
        scaffoldState = scaffoldState,
        navController = navController,
    ) { paddingValues ->
        NavHost(
            navController = navControllerAdditional,
            startDestination = additionalGraph,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = additionalGraph) {
                AdditionalLayout(modifier = modifier, viewModel = viewModel)
            }
            register(teacherFeature, navController)
            register(groupFeature, navController)
            register(settingsFeature, navController)
        }
    }
}

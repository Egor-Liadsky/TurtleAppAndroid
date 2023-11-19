package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.impl.presentation.teacher.screen.TeacherScreen
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import org.koin.androidx.compose.koinViewModel

class TeacherNavigationImpl : TeacherNavigation {

    override val baseRoute: String = "teacher"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val viewModel = koinViewModel<TeacherViewModel>()
            TeacherScreen(modifier, viewModel)
        }
    }
}

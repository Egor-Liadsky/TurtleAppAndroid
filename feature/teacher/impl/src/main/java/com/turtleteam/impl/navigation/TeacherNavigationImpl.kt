package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.impl.presentation.teacher.screen.TeacherScreen
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import org.koin.androidx.compose.koinViewModel

internal val teacherGraph = "teacher"

class TeacherNavigationImpl : TeacherNavigation {

    override val baseRoute: String = teacherGraph

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val viewModel = koinViewModel<TeacherViewModel>()
            TeacherScreen(modifier, viewModel, navController)
        }
    }
}

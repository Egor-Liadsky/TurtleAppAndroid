package com.turtleteam.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.impl.presentation.screen.teacher.TeacherScreen

class TeacherNavigationImpl : TeacherNavigation {

    override val baseRoute: String = "teacher"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            TeacherScreen(modifier)
        }
    }
}

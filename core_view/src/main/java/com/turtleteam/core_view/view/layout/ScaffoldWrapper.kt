package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.core_view.R
import com.turtleteam.core_view.navigation.BottomNavigationBar
import com.turtleteam.core_view.navigation.NavigationItem
import com.turtleteam.core_view.view.background.TurtlesBackground
import org.koin.compose.koinInject

@Composable
fun ScaffoldWrapper(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")

    val groupFeature: GroupNavigation = koinInject()
    val teacherFeature: TeacherNavigation = koinInject()
    val additionalFeature: AdditionalNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    val bottomNavigationItems = listOf(
        NavigationItem(
            route = groupFeature.baseRoute,
            icon = R.drawable.ic_calendar,
        ),
        NavigationItem(
            route = teacherFeature.baseRoute,
            icon = R.drawable.ic_teacher,
        ),
        NavigationItem(
            route = additionalFeature.baseRoute,
            icon = R.drawable.ic_menu,
        ),
        NavigationItem(
            route = settingsFeature.baseRoute,
            icon = R.drawable.ic_settings,
        ),
    )

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                routes = bottomNavigationItems,
                currentRoute = currentRoute,
                onClick = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(Modifier.fillMaxSize()) {
            TurtlesBackground()

            content(paddingValues)
        }
    }
}
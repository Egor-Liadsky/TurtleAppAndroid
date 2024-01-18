package com.turtleteam.turtleapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.R
import com.turtleteam.core_view.navigation.BottomNavigationBar
import com.turtleteam.core_view.navigation.NavigationItem
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.background.TurtlesBackground
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject

@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    isWelcome: Boolean,
    errorService: ErrorService = koinInject(),
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")

    val welcomeFeature: WelcomeNavigation = koinInject()
    val groupFeature: GroupNavigation = koinInject()
    val teacherFeature: TeacherNavigation = koinInject()
    val additionalFeature: AdditionalNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it, actionLabel = "Закрыть")
        }
    }

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
            if (currentRoute != welcomeFeature.baseRoute) {
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
        },
        snackbarHost = { snackbarHostState ->
            SnackbarHost(snackbarHostState, Modifier.zIndex(2f)) {
                Snackbar(
                    modifier = Modifier.zIndex(2f),
                    snackbarData = it,
                    backgroundColor = TurtleTheme.color.textColor,
                    actionColor = TurtleTheme.color.selectTextColor
                )
            }
        },
    ) { paddingValues ->
        val bottomNavigationViewModifier =
            Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        TurtlesBackground()
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            navController = navController,
            startDestination = if (isWelcome) welcomeFeature.baseRoute else groupFeature.baseRoute,
        ) {
            register(groupFeature, navController, bottomNavigationViewModifier)
            register(teacherFeature, navController, bottomNavigationViewModifier)
            register(additionalFeature, navController, bottomNavigationViewModifier)
            register(settingsFeature, navController, bottomNavigationViewModifier)
            register(welcomeFeature, navController)
        }
    }
}

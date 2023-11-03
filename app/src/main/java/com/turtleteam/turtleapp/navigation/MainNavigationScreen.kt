package com.turtleteam.turtleapp.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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
import com.turtleteam.storage.Storage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    errorService: ErrorService = koinInject(),
    storage: Storage = koinInject()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")

    val scope = rememberCoroutineScope()

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

    var welcomeStartDestination = false
    scope.launch { welcomeStartDestination = storage.getInstitution().isNullOrBlank() == true }

    val bottomNavigationItems = listOf(
        NavigationItem(
            route = groupFeature.baseRoute,
            icon = R.drawable.ic_group
        ),
        NavigationItem(
            route = teacherFeature.baseRoute,
            icon = R.drawable.ic_teacher
        ),
        NavigationItem(
            route = additionalFeature.baseRoute,
            icon = R.drawable.ic_additional
        ),
        NavigationItem(
            route = settingsFeature.baseRoute,
            icon = R.drawable.ic_settings
        )
    )

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
//            if (welcomeStartDestination){
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
                    }
                )
//            }
        },
        snackbarHost = { snackbarHostStatet ->
            SnackbarHost(snackbarHostStatet, Modifier.zIndex(2f)) {
                Snackbar(modifier = Modifier.zIndex(2f), snackbarData = it)
            }
        }
    ) { paddingValues ->
        val bottomNavigationViewModifier =
            Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        TurtlesBackground()
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            navController = navController,
            startDestination = groupFeature.baseRoute//if(!welcomeStartDestination) welcomeFeature.baseRoute else groupFeature.baseRoute
        ) {
//            composable(route = "splash"){
//                SplashScreen(navController = navController, isWelcome = true, welcomeRoute = , groupRoute = )
//            }
            register(groupFeature, navController, bottomNavigationViewModifier)
            register(teacherFeature, navController, bottomNavigationViewModifier)
            register(additionalFeature, navController, bottomNavigationViewModifier)
            register(settingsFeature, navController, bottomNavigationViewModifier)
            register(welcomeFeature, navController)
        }
    }
}

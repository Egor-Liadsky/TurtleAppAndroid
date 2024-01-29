package com.turtleteam.turtleapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.core_navigation.NavigationApi
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

    Log.e("TAGTAG", "MainNavigationScreen: $currentRoute", )

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it, actionLabel = "Закрыть")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { snackbarHostState ->
            SnackbarHost(snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    backgroundColor = TurtleTheme.color.textColor,
                    actionColor = TurtleTheme.color.selectTextColor
                )
            }
        },
    ) { paddingValues ->

        val bottomNavigationViewModifier =
            Modifier.padding(bottom = paddingValues.calculateBottomPadding())

        Box {
            TurtlesBackground()

            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                startDestination = if (isWelcome) welcomeFeature.baseRoute else groupFeature.baseRoute,
            ) {
                register(groupFeature, navController, bottomNavigationViewModifier)
                register(teacherFeature, navController)
                register(additionalFeature, navController)
                register(settingsFeature, navController)
                register(welcomeFeature, navController)
            }
        }
    }
}

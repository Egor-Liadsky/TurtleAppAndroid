package com.turtleteam.impl.presentation.teacher.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.layout.ModalBottomSheetLayoutWrapper
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.impl.navigation.teacherGraph
import com.turtleteam.impl.presentation.teacher.screen.layout.TeacherLayout
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

private val animDuration = 500

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeacherScreen(
    modifier: Modifier = Modifier,
    viewModel: TeacherViewModel,
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navControllerTeacher = rememberNavController()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch { modalBottomSheetState.hide() }
    }

    val groupFeature: GroupNavigation = koinInject()
    val additionalFeature: AdditionalNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    ModalBottomSheetLayoutWrapper(
        scaffoldState = scaffoldState,
        modalBottomSheetState = modalBottomSheetState,
        navController = navController,
        sheetContent = {
            SheetWrapper(background = TurtleTheme.color.groupSheetTopBar) {
                GroupSheet(
                    sheetState = modalBottomSheetState,
                    textFieldValue = state.value.textFieldValue,
                    onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                    loadingState = state.value.teachersLoadingState,
                    groups = state.value.teachers ?: listOf(),
                    selectedGroup = state.value.selectedTeacher ?: "",
                    onRefresh = { viewModel.onRefreshTeachers() },
                    onClearValueClick = { viewModel.onTextFieldValueChanged("") }
                ) {
                    viewModel.onSelectTeacherClick(it)
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navControllerTeacher,
            startDestination = teacherGraph,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = teacherGraph,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                }) {
                TeacherLayout(
                    modifier = modifier,
                    viewModel = viewModel,
                    modalBottomSheetState
                )
            }
            register(groupFeature, navController)
            register(additionalFeature, navController)
            register(settingsFeature, navController)
        }
    }
}

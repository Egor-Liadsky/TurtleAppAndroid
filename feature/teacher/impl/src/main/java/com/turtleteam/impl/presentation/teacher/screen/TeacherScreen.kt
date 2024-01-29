package com.turtleteam.impl.presentation.teacher.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.view.layout.BottomSheetScaffoldWrapper
import com.turtleteam.core_view.view.layout.ScaffoldWrapper
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.impl.navigation.teacherGraph
import com.turtleteam.impl.presentation.teacher.screen.layout.TeacherLayout
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeacherScreen(
    modifier: Modifier = Modifier,
    viewModel: TeacherViewModel,
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navControllerTeacher = rememberNavController()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    val groupFeature: GroupNavigation = koinInject()
    val additionalFeature: AdditionalNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    BottomSheetScaffoldWrapper(
        scaffoldState = scaffoldState,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        navController = navController,
        sheetContent = {
            SheetWrapper(background = Color(0xFFfcfdd3)) {
                GroupSheet(
                    sheetState = bottomSheetScaffoldState.bottomSheetState,
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
            composable(route = teacherGraph) {
                TeacherLayout(
                    modifier = modifier,
                    viewModel = viewModel,
                    bottomSheetScaffoldState.bottomSheetState
                )
            }
            register(groupFeature, navController)
            register(additionalFeature, navController)
            register(settingsFeature, navController)
        }
    }
}

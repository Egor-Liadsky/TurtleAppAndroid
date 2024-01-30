package com.turtleteam.impl.presentation.group.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
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
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.view.layout.ModalBottomSheetLayoutWrapper
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.impl.navigation.groupGraph
import com.turtleteam.impl.presentation.group.screen.layout.GroupLayout
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupScreen(
    modifier: Modifier = Modifier,
    viewModel: GroupViewModel,
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navControllerGroup = rememberNavController()
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val teacherFeature: TeacherNavigation = koinInject()
    val additionalFeature: AdditionalNavigation = koinInject()
    val settingsFeature: SettingsNavigation = koinInject()

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch { modalBottomSheetState.hide() }
    }

    ModalBottomSheetLayoutWrapper(
        scaffoldState = scaffoldState,
        modalBottomSheetState = modalBottomSheetState,
        navController = navController,
        sheetContent = {
            SheetWrapper(background = Color(0xFFfcfdd3)) {
                GroupSheet(
                    sheetState = modalBottomSheetState,
                    textFieldValue = state.value.textFieldValue,
                    onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                    loadingState = state.value.groupsLoadingState,
                    groups = state.value.groups ?: listOf(),
                    selectedGroup = state.value.selectedGroup ?: "",
                    onRefresh = { viewModel.onRefreshGroups() },
                    onClearValueClick = { viewModel.onTextFieldValueChanged("") }
                ) {
                    viewModel.onSelectGroupClick(it)
                }
            }
        }
    ) { paddingValues ->
        Box {
            NavHost(
                navController = navControllerGroup,
                startDestination = groupGraph,
                modifier = modifier.padding(paddingValues)
            ) {
                composable(route = groupGraph) {
                    GroupLayout(
                        modifier = Modifier,
                        viewModel = viewModel,
                        sheetState = modalBottomSheetState
                    )
                }
                register(teacherFeature, navController)
                register(additionalFeature, navController)
                register(settingsFeature, navController)
            }
        }
    }
}

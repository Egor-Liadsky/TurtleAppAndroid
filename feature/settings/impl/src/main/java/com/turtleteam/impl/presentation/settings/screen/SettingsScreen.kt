package com.turtleteam.impl.presentation.settings.screen

import androidx.activity.compose.BackHandler
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turtleteam.api.models.Institution
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.view.layout.ModalBottomSheetLayoutWrapper
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.impl.navigation.menuRoute
import com.turtleteam.impl.presentation.settings.screen.layout.SettingsLayout
import com.turtleteam.impl.presentation.settings.screen.sheets.ChangeInstitutionSheet
import com.turtleteam.impl.presentation.settings.screen.sheets.ThemeSheet
import com.turtleteam.impl.presentation.settings.state.SettingsButton
import com.turtleteam.impl.presentation.settings.viewModel.SettingsViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navControllerSettings = rememberNavController()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch { modalBottomSheetState.hide() }
    }

    val settingsFeature: SettingsNavigation = koinInject()

    ModalBottomSheetLayoutWrapper(
        scaffoldState = scaffoldState,
        modalBottomSheetState = modalBottomSheetState,
        navController = navController,
        sheetContent = {
            when (state.value.selectedSettingsButton) {
                SettingsButton.CHANGE_INSTITUTION -> {
                    SheetWrapper(title = "Ваше учебное заведение") {
                        ChangeInstitutionSheet(
                            loadingState = state.value.institutionLoadingState,
                            institutions = state.value.institutions ?: listOf(),
                            selectedInstitution = state.value.selectedInstitution ?: Institution(),
                            onRefresh = { viewModel.onRefreshInstitutions() },
                            onSelectInstitutionClick = { viewModel.onSelectInstitution(it) }
                        )
                    }
                }

                SettingsButton.NOTIFICATIONS -> {}
                SettingsButton.CHANGE_THEME -> {
                    SheetWrapper(title = "Тема оформления") {
                        ThemeSheet(
                            selectedIsDarkTheme = state.value.selectedThemeIsDark ?: false,
                            onThemeSelected = {
                                viewModel.onSelectThemeClick(it)
                            },
                        )
                    }
                }

                SettingsButton.CHANGE_LANGUAGE -> {}
                else -> {}
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navControllerSettings,
            startDestination = menuRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = menuRoute) {
                SettingsLayout(
                    modifier = modifier,
                    viewModel = viewModel,
                    sheetState = modalBottomSheetState
                )
            }
            register(settingsFeature, navController)
        }
    }
}

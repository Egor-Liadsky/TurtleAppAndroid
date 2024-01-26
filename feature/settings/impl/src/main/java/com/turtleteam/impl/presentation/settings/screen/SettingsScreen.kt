package com.turtleteam.impl.presentation.settings.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.api.models.Institution
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.settings.screen.components.CategoryItem
import com.turtleteam.impl.presentation.settings.screen.components.MenuInfo
import com.turtleteam.impl.presentation.settings.screen.sheets.ChangeInstitutionSheet
import com.turtleteam.impl.presentation.settings.screen.sheets.ThemeSheet
import com.turtleteam.impl.presentation.settings.state.SettingsButton
import com.turtleteam.impl.presentation.settings.viewModel.SettingsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
        )

    BackHandler {
        if (sheetState.isVisible) {
            scope.launch { sheetState.hide() }
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetBackgroundColor = TurtleTheme.color.sheetBackground,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
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
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .then(modifier),
        ) {
            CommonTopBar(title = "Turtle Schedule")

            val settingsList =
                listOf(
                    MenuInfo(title = "Изменить учебное заведение", onClick = {
                        viewModel.onSelectButtonClick(settingsButton = SettingsButton.CHANGE_INSTITUTION)
                        scope.launch { sheetState.show() }
                        viewModel.onChangeThemeClick()
                    }),
                    MenuInfo(title = "Уведомления", onClick = { }),
                    MenuInfo(title = "Тема приложения", onClick = {
                        viewModel.onSelectButtonClick(settingsButton = SettingsButton.CHANGE_THEME)
                        scope.launch { sheetState.show() }
                    }),
//                    MenuInfo(title = "Язык", onClick = { }),
                )

            val additionalInfo =
                listOf(
                    MenuInfo(title = "Обратная связь", onClick = { viewModel.onFeedbackClick() }),
                    MenuInfo(title = "О приложении", onClick = { viewModel.onAboutAppClick() }),
                )

            LazyColumn(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                item {
                    CategoryItem(title = "Настройки", list = settingsList)
                }

                item {
                    CategoryItem(title = "Дополнительно", list = additionalInfo)
                }
            }
        }
    }
}

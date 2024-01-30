package com.turtleteam.impl.presentation.settings.screen.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.settings.screen.components.CategoryItem
import com.turtleteam.impl.presentation.settings.screen.components.MenuInfo
import com.turtleteam.impl.presentation.settings.state.SettingsButton
import com.turtleteam.impl.presentation.settings.viewModel.SettingsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsLayout(
    modifier: Modifier,
    viewModel: SettingsViewModel,
    sheetState: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        CommonTopBar(title = "Turtle Schedule")

        val settingsList =
            listOf(
                MenuInfo(title = "Изменить учебное заведение", onClick = {
                    scope.launch { sheetState.show() }
                    viewModel.onSelectButtonClick(settingsButton = SettingsButton.CHANGE_INSTITUTION)
                    viewModel.onChangeInstitutionClick()
                }),
                MenuInfo(title = "Уведомления", onClick = {
                    viewModel.onNotificationsClick()
                }),
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
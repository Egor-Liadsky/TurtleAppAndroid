package com.turtleteam.impl.presentation.presentation.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.presentation.settings.screen.components.CategoryItem
import com.turtleteam.impl.presentation.presentation.settings.screen.components.MenuInfo

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)){
        CommonTopBar(title = "Turtle Schedule")

        val settingsList = listOf(
            MenuInfo(title = "Изменить учебное заведение", onClick = {  }),
            MenuInfo(title = "Изменить группу", onClick = {  }),
            MenuInfo(title = "Уведомления", onClick = {  }),
            MenuInfo(title = "Тема приложения", onClick = {  }),
            MenuInfo(title = "Язык", onClick = {  }),
        )

        val additionalInfo = listOf(
            MenuInfo(title = "Обратная связь", onClick = {  }),
            MenuInfo(title = "О приложении", onClick = {  }),
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

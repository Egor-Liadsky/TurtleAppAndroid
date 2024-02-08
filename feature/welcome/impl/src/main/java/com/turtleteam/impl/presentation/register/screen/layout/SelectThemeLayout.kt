package com.turtleteam.impl.presentation.register.screen.layout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.button.SelectButton
import com.turtleteam.core_view.view.frame.ScheduleSelectFrame
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel

@Composable
fun SelectThemeLayout(viewModel: RegisterViewModel) {
    val state = viewModel.state.collectAsState()

    BackHandler {
        viewModel.onBackAction()
    }

    ScheduleSelectFrame(
        image = TurtleTheme.images.selectTheme,
    ) {
        SelectButton(
            Modifier.padding(top = 10.dp),
            title = "Светлая",
            isSelected = state.value.selectedThemeIsDark == false,
        ) {
            viewModel.onSelectThemeClick(isDark = false)
        }

        SelectButton(
            Modifier.padding(top = 5.dp),
            title = "Темная",
            isSelected = state.value.selectedThemeIsDark == true,
        ) {
            viewModel.onSelectThemeClick(isDark = true)
        }
    }
}

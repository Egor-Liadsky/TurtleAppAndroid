package com.turtleteam.impl.presentation.screen.register.screen.layout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.view.button.SelectButton
import com.turtleteam.core_view.view.frame.ScheduleSelectFrame
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel

@Composable
fun SelectThemeLayout(viewModel: RegisterViewModel) {
    val state = viewModel.state.collectAsState()

    BackHandler {
        viewModel.onBackAction()
    }

    ScheduleSelectFrame(
        image = R.drawable.ic_choose_theme,
    ) {
        SelectButton(
            Modifier.padding(top = 10.dp),
            title = "Светлая",
            isSelected = state.value.selectThemeIsDark == false,
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.sun),
                    contentDescription = null,
                    tint = LocalColors.current.buttonSelectTurtle,
                    modifier = Modifier.size(40.dp).padding(end = 16.dp),
                )
            },
        ) {
            viewModel.onSelectThemeClick(isDark = false)
        }

        SelectButton(
            Modifier.padding(top = 5.dp),
            title = "Темная",
            isSelected = state.value.selectThemeIsDark == true,
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.moon),
                    contentDescription = null,
                    tint = LocalColors.current.buttonSelectTurtle,
                    modifier = Modifier.size(40.dp).padding(end = 16.dp),
                )
            },
        ) {
            viewModel.onSelectThemeClick(isDark = true)
        }
    }
}

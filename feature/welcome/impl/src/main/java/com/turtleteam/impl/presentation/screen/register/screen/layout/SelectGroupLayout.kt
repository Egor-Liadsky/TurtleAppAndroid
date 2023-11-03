package com.turtleteam.impl.presentation.screen.register.screen.layout

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.frame.ScheduleSelectFrame
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel

@Composable
fun SelectGroupLayout(viewModel: RegisterViewModel) {

    BackHandler {
        viewModel.onBackAction()
    }

    Box {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScheduleSelectFrame(title = "группу", isGroup = true, selectButtonTitle = "Выбрать") {

            }
        }
        CommonButton(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 46.dp, start = 32.dp, end = 32.dp),
            title = "К РАСПИСАНИЮ!",
            textColor = TurtleTheme.color.commonButtonTextColor,
            background = TurtleTheme.color.commonButtonBackground,
            indicationColor = TurtleTheme.color.commonButtonTextColor,
        ) {
            viewModel.onNextClick()
        }
    }
}

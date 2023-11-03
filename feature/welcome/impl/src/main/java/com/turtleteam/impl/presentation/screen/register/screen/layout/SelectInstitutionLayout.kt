package com.turtleteam.impl.presentation.screen.register.screen.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.model.Institution
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.frame.ScheduleSelectFrame
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectInstitutionLayout(viewModel: RegisterViewModel, sheetState: ModalBottomSheetState) {

    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Box {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScheduleSelectFrame(
                title = "учебное заведение",
                selectButtonTitle = if (state.value.selectInstitution == null) "Выбрать" else state.value.selectInstitution!!.title
            ) {
                viewModel.onInstitutionClick()
                scope.launch { sheetState.show() }
            }
        }
        CommonButton(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 46.dp, start = 32.dp, end = 32.dp),
            title = "ДАЛЕЕ",
            textColor = TurtleTheme.color.commonButtonTextColor,
            background = TurtleTheme.color.commonButtonBackground,
            indicationColor = TurtleTheme.color.commonButtonTextColor,
        ) {
            viewModel.onNextClick()
        }
    }
}

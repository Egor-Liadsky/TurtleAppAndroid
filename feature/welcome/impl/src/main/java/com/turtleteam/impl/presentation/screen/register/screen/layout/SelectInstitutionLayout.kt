package com.turtleteam.impl.presentation.screen.register.screen.layout

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.view.button.SelectButton
import com.turtleteam.core_view.view.frame.ScheduleSelectFrame
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectInstitutionLayout(viewModel: RegisterViewModel, sheetState: ModalBottomSheetState) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    ScheduleSelectFrame(
        image = R.drawable.ic_choose_college,
    ) {
        SelectButton(
            Modifier.padding(top = 10.dp),
            title = if (state.value.selectInstitution == null) "Выбрать" else state.value.selectInstitution!!.title,
            isSelected = state.value.selectInstitution != null,
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_turtle_select),
                    contentDescription = null,
                    tint = LocalColors.current.buttonSelectTurtle,
                )
            },
        ) {
            viewModel.onInstitutionClick()
            scope.launch { sheetState.show() }
        }
    }
}

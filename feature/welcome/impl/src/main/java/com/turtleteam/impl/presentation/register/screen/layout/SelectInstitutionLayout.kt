package com.turtleteam.impl.presentation.register.screen.layout

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetState
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
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectInstitutionLayout(viewModel: RegisterViewModel, sheetState: ModalBottomSheetState) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    BackHandler {
        if (sheetState.isVisible) {
            scope.launch { sheetState.hide() }
        } else {
            viewModel.onBackAction()
        }
    }

    ScheduleSelectFrame(
        image = R.drawable.ic_choose_college,
    ) {
        SelectButton(
            Modifier.padding(top = 10.dp),
            title = if (state.value.selectedInstitution?.port == null) {
                "Выбрать"
            } else {
                state.value.selectedInstitution!!.title
                    ?: ""
            },
            isSelected = state.value.selectedInstitution?.port != null,
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

package com.turtleteam.impl.presentation.group.screen.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.view.layout.EmptyLayout
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.layout.LoadingLayout
import com.turtleteam.core_view.view.layout.ScheduleLayout
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.core_view.view.topbar.SelectGroupTopBar
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupLayout(modifier: Modifier, viewModel: GroupViewModel, sheetState: ModalBottomSheetState) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize(),
    ) {
        SelectGroupTopBar(
            selectedGroup = state.value.selectedGroup ?: "Выбрать",
        ) {
            scope.launch { sheetState.show() }
            viewModel.onGroupClick()
        }

        when (state.value.scheduleLoading) {
            LoadingState.Loading -> LoadingLayout()

            LoadingState.Success -> {
                state.value.schedule?.let {
                    ScheduleLayout(data = it)
                }
            }

            LoadingState.Empty -> {
                EmptyLayout(
                    image = R.drawable.ic_select_group_empty,
                    title = "Выберите группу"
                )
            }

            is LoadingState.Error -> ErrorLayout {
                viewModel.onRefreshSchedule()
            }
        }
    }
}
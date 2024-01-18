package com.turtleteam.impl.presentation.group.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.turtleteam.core_view.R
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
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
fun GroupScreen(modifier: Modifier = Modifier, viewModel: GroupViewModel) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetBackgroundColor = TurtleTheme.color.sheetBackground,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            SheetWrapper {
                GroupSheet(
                    sheetState = sheetState,
                    textFieldValue = state.value.textFieldValue,
                    onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                    loadingState = state.value.groupsLoadingState,
                    groups = state.value.groups ?: listOf(),
                    selectedGroup = state.value.selectedGroup ?: "",
                    onRefresh = { viewModel.onRefreshGroups() },
                    onClearValueClick = { viewModel.onTextFieldValueChanged("") }
                ) {
                    viewModel.onSelectGroupClick(it)
                }
            }
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .then(modifier),
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
}

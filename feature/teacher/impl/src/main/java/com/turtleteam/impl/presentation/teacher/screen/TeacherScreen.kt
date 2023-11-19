package com.turtleteam.impl.presentation.teacher.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.layout.EmptyLayout
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.layout.ScheduleLayout
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.topbar.SelectGroupTopBar
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeacherScreen(modifier: Modifier = Modifier, viewModel: TeacherViewModel) {
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Divider(
                    Modifier
                        .width(22.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = TurtleTheme.color.divider,
                )
                Column(Modifier.padding(top = 16.dp)) {
                    GroupSheet(
                        sheetState = sheetState,
                        textFieldValue = state.value.textFieldValue,
                        onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                        loadingState = state.value.teachersLoadingState,
                        groups = state.value.teachers ?: listOf(),
                        selectedGroup = state.value.selectedTeacher ?: "",
                    ) {
                        viewModel.onSelectTeacherClick(it)
                    }
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
                selectedGroup = state.value.selectedTeacher ?: "Выбрать",
            ) {
                scope.launch { sheetState.show() }
                viewModel.onTeacherClick()
            }

            when (state.value.scheduleLoading) {
                LoadingState.Loading -> {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(
                            Modifier.size(24.dp),
                            color = TurtleTheme.color.textColor,
                        )
                    }
                }

                LoadingState.Success -> {
                    state.value.schedule?.let {
                        ScheduleLayout(data = it)
                    }
                }

                LoadingState.Empty -> {
                    EmptyLayout(title = "Выберите преподавателя")
                }

                is LoadingState.Error -> ErrorLayout()
            }
        }
    }
}
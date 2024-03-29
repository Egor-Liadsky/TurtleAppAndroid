package com.turtleteam.impl.presentation.teacher.screen.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.turtleteam.core_view.R
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.layout.EmptyLayout
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.layout.LoadingLayout
import com.turtleteam.core_view.view.layout.ScheduleLayout
import com.turtleteam.core_view.view.topbar.SelectGroupTopBar
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeacherLayout(
    modifier: Modifier,
    viewModel: TeacherViewModel,
    bottomSheetState: ModalBottomSheetState
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        SelectGroupTopBar(
            selectedGroup = state.value.selectedTeacher ?: "Выбрать",
        ) {
            scope.launch { bottomSheetState.show() }
            viewModel.onTeacherClick()
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
                    image = TurtleTheme.images.selectGroupEmpty,
                    title = "Выберите\nпреподавателя"
                )
            }

            is LoadingState.Error -> ErrorLayout {
                viewModel.onRefreshSchedule()
            }
        }
    }
}
package com.turtleteam.impl.presentation.teacher.state

import com.turtleteam.api.models.Schedule
import com.turtleteam.core_view.state.LoadingState

data class TeacherState(
    val selectedTeacher: String? = null,
    val schedule: Schedule? = null,
    val scheduleLoading: LoadingState = LoadingState.Success,
    val textFieldValue: String = "",
    val teachers: List<String>? = null,
    val teachersLoadingState: LoadingState = LoadingState.Success,
)

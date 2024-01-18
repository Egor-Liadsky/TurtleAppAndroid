package com.turtleteam.impl.presentation.group.state

import com.turtleteam.api.models.Schedule
import com.turtleteam.core_view.state.LoadingState

data class GroupState(
    val selectedGroup: String? = null,
    val schedule: Schedule? = null,
    val scheduleLoading: LoadingState = LoadingState.Success,
    val textFieldValue: String = "",
    val groups: List<String>? = null,
    val groupsLoadingState: LoadingState = LoadingState.Success,
)

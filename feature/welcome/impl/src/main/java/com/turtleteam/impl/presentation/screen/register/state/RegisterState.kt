package com.turtleteam.impl.presentation.screen.register.state

import com.turtleteam.core_view.model.GroupAndTeacher
import com.turtleteam.core_view.model.Institution
import com.turtleteam.core_view.state.LoadingState

data class RegisterState(
    val stage: Int = 1,
    val institutions: List<Institution>? = null,
    val groups: List<String>? = null,
    val institutionLoadingState: LoadingState = LoadingState.Success,
    val groupsLoadingState: LoadingState = LoadingState.Success,
    val selectInstitution: Institution? = null,
    val selectThemeIsDark: Boolean? = null,
    val selectGroup: String? = null,
    val port: String? = null
)

package com.turtleteam.impl.presentation.register.state

import com.turtleteam.api.models.Institution
import com.turtleteam.core_view.state.LoadingState

data class RegisterState(
    val stage: Int = 1,
    val institutions: List<Institution>? = null,
    val groups: List<String>? = null,
    val institutionLoadingState: LoadingState = LoadingState.Success,
    val groupsLoadingState: LoadingState = LoadingState.Success,
    val selectedInstitution: Institution? = null,
    val selectedThemeIsDark: Boolean? = null,
    val selectedGroup: String? = null,
    val port: String? = null,
    val textFieldValue: String = ""
)

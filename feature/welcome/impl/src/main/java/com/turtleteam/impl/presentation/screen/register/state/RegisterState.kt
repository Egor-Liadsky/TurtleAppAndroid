package com.turtleteam.impl.presentation.screen.register.state

import com.turtleteam.api.data.model.Institution
import com.turtleteam.core_view.state.LoadingState

data class RegisterState(
    val stage: Int = 1,
    val institutions: List<Institution>? = null,
    val institutionLoadingState: LoadingState = LoadingState.Success,
    val selectInstitution: Institution? = null
)

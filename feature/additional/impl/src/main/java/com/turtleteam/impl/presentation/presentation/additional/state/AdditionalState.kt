package com.turtleteam.impl.presentation.presentation.additional.state

import com.turtleteam.api.models.Ring
import com.turtleteam.core_view.state.LoadingState

data class AdditionalState(
    val ringsOpened: Boolean = false,
    val ringsLoadingState: LoadingState = LoadingState.Success,
    val rings: Ring? = null,
    val planshetkaUrl: String? = null
)
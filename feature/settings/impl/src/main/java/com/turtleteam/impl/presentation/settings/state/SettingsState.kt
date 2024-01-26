package com.turtleteam.impl.presentation.settings.state

import com.turtleteam.api.models.Institution
import com.turtleteam.core_view.state.LoadingState

data class SettingsState(
    val selectedThemeIsDark: Boolean? = null,
    val selectedInstitution: Institution? = null,
    val selectedSettingsButton: SettingsButton? = null,
    val institutions: List<Institution>? = null,
    val institutionLoadingState: LoadingState = LoadingState.Loading
)

enum class SettingsButton {
    CHANGE_INSTITUTION,
    NOTIFICATIONS,
    CHANGE_THEME,
    CHANGE_LANGUAGE
}
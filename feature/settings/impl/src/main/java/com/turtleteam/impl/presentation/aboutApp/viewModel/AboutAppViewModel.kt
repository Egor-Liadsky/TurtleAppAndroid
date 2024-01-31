package com.turtleteam.impl.presentation.aboutApp.viewModel

import androidx.lifecycle.ViewModel
import com.lyadsky.analytics.AnalyticsService
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam
import com.turtleteam.api.config.ConfigService
import com.turtleteam.impl.navigation.SettingsNavigator
import com.turtleteam.impl.presentation.aboutApp.state.AboutAppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AboutAppViewModel(
    private val navigator: SettingsNavigator,
    private val configService: ConfigService,
    private val analyticsService: AnalyticsService
) : ViewModel() {

    private val _state = MutableStateFlow(AboutAppState())
    val state = _state.asStateFlow()

    init {
        analyticsService.sendEvent(
            event = LogEvent.OPEN_SCREEN,
            params = mapOf(
                LogEventParam.SCREEN_NAME to "About app screen",
            )
        )
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun getVersionName() {
        val versionName = configService.getVersionName()
        _state.update { it.copy(versionName = versionName) }
    }
}
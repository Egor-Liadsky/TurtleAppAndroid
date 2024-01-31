package com.turtleteam.impl.presentation.feedback.viewModel

import androidx.lifecycle.ViewModel
import com.lyadsky.analytics.AnalyticsService
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam
import com.turtleteam.api.config.ConfigService
import com.turtleteam.impl.navigation.SettingsNavigator
import com.turtleteam.impl.presentation.feedback.state.FeedbackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedbackViewModel(
    private val navigator: SettingsNavigator,
    private val configService: ConfigService,
    private val analyticsService: AnalyticsService
) : ViewModel() {

    private val _state = MutableStateFlow(FeedbackState())
    val state = _state.asStateFlow()

    init {
        analyticsService.sendEvent(
            event = LogEvent.OPEN_SCREEN,
            params = mapOf(
                LogEventParam.SCREEN_NAME to "Feedback screen",
            )
        )
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun getFeedbackEmail() {
        val feedbackEmail = configService.getFeedbackEmail()
        _state.update { it.copy(feedbackEmail = feedbackEmail) }
    }
}
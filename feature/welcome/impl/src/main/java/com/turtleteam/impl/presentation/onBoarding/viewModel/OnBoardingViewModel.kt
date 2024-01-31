package com.turtleteam.impl.presentation.onBoarding.viewModel

import androidx.lifecycle.ViewModel
import com.lyadsky.analytics.AnalyticsService
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam
import com.turtleteam.impl.navigation.WelcomeNavigator

class OnBoardingViewModel(
    private val navigator: WelcomeNavigator,
    private val analyticsService: AnalyticsService
) : ViewModel() {

    init {
        analyticsService.sendEvent(
            event = LogEvent.OPEN_SCREEN,
            params = mapOf(
                LogEventParam.SCREEN_NAME to "On boarding screen",
            )
        )
    }

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }
}

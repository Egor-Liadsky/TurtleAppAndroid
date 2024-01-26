package com.turtleteam.impl.presentation.feedback.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.api.config.ConfigService
import com.turtleteam.impl.navigation.SettingsNavigator
import com.turtleteam.impl.presentation.feedback.state.FeedbackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedbackViewModel(
    private val navigator: SettingsNavigator,
    private val configService: ConfigService
) : ViewModel() {

    private val _state = MutableStateFlow(FeedbackState())
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun getFeedbackEmail() {
        val feedbackEmail = configService.getFeedbackEmail()
        _state.update { it.copy(feedbackEmail = feedbackEmail) }
    }
}
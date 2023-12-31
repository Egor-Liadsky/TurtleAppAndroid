package com.turtleteam.impl.presentation.presentation.onBoarding.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.presentation.navigation.WelcomeNavigator

class OnBoardingViewModel(private val navigator: WelcomeNavigator): ViewModel() {

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }
}

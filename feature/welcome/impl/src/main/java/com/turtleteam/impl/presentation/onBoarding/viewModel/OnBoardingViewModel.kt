package com.turtleteam.impl.presentation.onBoarding.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.WelcomeNavigator

class OnBoardingViewModel(private val navigator: WelcomeNavigator): ViewModel() {

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }
}

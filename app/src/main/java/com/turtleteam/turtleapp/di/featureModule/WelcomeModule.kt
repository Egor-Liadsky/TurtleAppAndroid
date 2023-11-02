package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.navigation.WelcomeNavigationImpl
import com.turtleteam.impl.presentation.navigation.WelcomeNavigator
import com.turtleteam.impl.presentation.screen.onBoarding.viewModel.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val welcomeModule = module {
    single<WelcomeNavigation> { WelcomeNavigationImpl() }
    factory { navController ->
        WelcomeNavigator(get(), navController.get())
    }
    viewModel { params ->
        OnBoardingViewModel(params.get())
    }
}

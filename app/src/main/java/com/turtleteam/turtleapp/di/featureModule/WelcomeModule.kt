package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.data.repository.WelcomeRepository
import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.data.repository.WelcomeRepositoryImpl
import com.turtleteam.impl.navigation.WelcomeNavigationImpl
import com.turtleteam.impl.navigation.WelcomeNavigator
import com.turtleteam.impl.presentation.presentation.onBoarding.viewModel.OnBoardingViewModel
import com.turtleteam.impl.presentation.presentation.register.viewModel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val welcomeModule = module {
    single<WelcomeNavigation> { WelcomeNavigationImpl() }
    factory<WelcomeRepository> { WelcomeRepositoryImpl(get()) }
    factory { navController ->
        WelcomeNavigator(get(), get(), navController.get())
    }
    viewModel { params ->
        OnBoardingViewModel(params.get())
    }
    viewModel { params ->
        RegisterViewModel(params.get(), get(), get())
    }
}

package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.WelcomeNavigation
import com.turtleteam.impl.presentation.navigation.WelcomeNavigationImpl
import org.koin.dsl.module

val welcomeModule = module {
    single<WelcomeNavigation> { WelcomeNavigationImpl() }
}

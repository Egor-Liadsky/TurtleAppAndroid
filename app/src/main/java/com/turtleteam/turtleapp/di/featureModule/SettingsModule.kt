package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.impl.presentation.navigation.SettingsNavigationImpl
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsNavigation> { SettingsNavigationImpl() }
}

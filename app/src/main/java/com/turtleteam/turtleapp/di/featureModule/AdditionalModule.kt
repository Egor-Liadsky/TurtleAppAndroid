package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.impl.presentation.navigation.AdditionalNavigationImpl
import org.koin.dsl.module

val additionalModule = module {
    single<AdditionalNavigation> { AdditionalNavigationImpl() }
}

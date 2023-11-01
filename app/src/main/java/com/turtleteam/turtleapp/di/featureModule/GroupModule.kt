package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.impl.presentation.navigation.GroupNavigationImpl
import org.koin.dsl.module

val groupModule = module {
    single<GroupNavigation> { GroupNavigationImpl() }
}

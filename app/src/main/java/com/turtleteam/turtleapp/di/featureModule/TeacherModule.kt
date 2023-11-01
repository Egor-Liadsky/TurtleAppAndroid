package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.impl.presentation.navigation.TeacherNavigationImpl
import org.koin.dsl.module

val teacherModule = module {
    single<TeacherNavigation> { TeacherNavigationImpl() }
}

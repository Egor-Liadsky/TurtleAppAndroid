package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.data.repository.TeacherRepository
import com.turtleteam.api.navigation.TeacherNavigation
import com.turtleteam.impl.data.repository.TeacherRepositoryImpl
import com.turtleteam.impl.navigation.TeacherNavigationImpl
import com.turtleteam.impl.navigation.TeacherNavigator
import com.turtleteam.impl.presentation.teacher.viewModel.TeacherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teacherModule = module {
    single<TeacherRepository> { TeacherRepositoryImpl(get()) }
    single<TeacherNavigation> { TeacherNavigationImpl() }
    single<TeacherNavigator> { TeacherNavigator(get()) }
    viewModel { TeacherViewModel(get()) }
}

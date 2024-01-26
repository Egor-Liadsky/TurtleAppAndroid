package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.data.repository.GroupRepository
import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.impl.data.repository.GroupRepositoryImpl
import com.turtleteam.impl.navigation.GroupNavigationImpl
import com.turtleteam.impl.navigation.GroupNavigator
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupModule = module {
    single<GroupNavigation> { GroupNavigationImpl() }
    single<GroupRepository> { GroupRepositoryImpl(get()) }
    single { navController -> GroupNavigator(navController.get()) }
    viewModel { params ->
        GroupViewModel(params.get())
    }
}

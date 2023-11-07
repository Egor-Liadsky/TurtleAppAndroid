package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.navigation.GroupNavigation
import com.turtleteam.impl.navigation.GroupNavigationImpl
import com.turtleteam.impl.navigation.GroupNavigator
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupModule = module {
    single<GroupNavigation> { GroupNavigationImpl() }
    factory { navController ->
        GroupNavigator(get(), navController.get())
    }
    viewModel { params ->
        GroupViewModel(params.get())
    }
}

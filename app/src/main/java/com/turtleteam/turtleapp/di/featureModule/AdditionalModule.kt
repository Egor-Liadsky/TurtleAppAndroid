package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.data.repository.AdditionalRepository
import com.turtleteam.api.navigation.AdditionalNavigation
import com.turtleteam.impl.presentation.data.AdditionalRepositoryImpl
import com.turtleteam.impl.presentation.navigation.AdditionalNavigationImpl
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val additionalModule = module {
    viewModel { AdditionalViewModel() }
    single<AdditionalRepository> { AdditionalRepositoryImpl(get()) }
    single<AdditionalNavigation> { AdditionalNavigationImpl() }
}

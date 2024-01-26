package com.turtleteam.turtleapp.di.featureModule

import com.turtleteam.api.data.repository.SettingsRepository
import com.turtleteam.api.navigation.SettingsNavigation
import com.turtleteam.impl.data.repository.SettingsRepositoryImpl
import com.turtleteam.impl.navigation.SettingsNavigationImpl
import com.turtleteam.impl.navigation.SettingsNavigator
import com.turtleteam.impl.presentation.aboutApp.viewModel.AboutAppViewModel
import com.turtleteam.impl.presentation.feedback.viewModel.FeedbackViewModel
import com.turtleteam.impl.presentation.settings.viewModel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsNavigation> { SettingsNavigationImpl() }
    single { SettingsNavigator(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get(), get()) }
    viewModel { AboutAppViewModel(get(), get()) }
    viewModel { FeedbackViewModel(get(), get()) }
}

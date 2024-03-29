package com.turtleteam.impl.presentation.settings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyadsky.analytics.AnalyticsService
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam
import com.turtleteam.api.data.repository.SettingsRepository
import com.turtleteam.api.network.error.exceptionHandleable
import com.turtleteam.api.models.Institution
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.navigation.SettingsNavigator
import com.turtleteam.impl.presentation.settings.state.SettingsButton
import com.turtleteam.impl.presentation.settings.state.SettingsState
import com.turtleteam.storage.InstitutionDataStore
import com.turtleteam.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val storage: Storage,
    private val institutionDataStore: InstitutionDataStore,
    private val repository: SettingsRepository,
    private val navigator: SettingsNavigator,
    private val errorService: ErrorService,
    private val analyticsService: AnalyticsService
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val theme = storage.getTheme()
            _state.update {
                it.copy(
                    selectedThemeIsDark = theme,
                    selectedInstitution = institutionDataStore.getInstitution(),
                )
            }
            analyticsService.sendEvent(
                event = LogEvent.OPEN_SCREEN,
                params = mapOf(
                    LogEventParam.SCREEN_NAME to "Settings screen",
                )
            )
        }
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onSelectThemeClick(isDark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.setTheme(isDark)
            _state.update { it.copy(selectedThemeIsDark = isDark) }
        }
    }

    fun onSelectButtonClick(settingsButton: SettingsButton) {
        _state.update { it.copy(selectedSettingsButton = settingsButton) }
    }

    fun onSelectInstitution(institution: Institution) {
        viewModelScope.launch(Dispatchers.IO) {
            institutionDataStore.saveInstitution(institution)
            storage.saveGroup("")
            storage.saveTeacher("")
            _state.update { it.copy(selectedInstitution = institution) }
        }
    }

    fun onAboutAppClick() {
        navigator.navigateToAboutApp()
    }

    fun onFeedbackClick() {
        navigator.navigateToFeedback()
    }

    fun onRefreshInstitutions() {
        getInstitutions()
    }

    fun onChangeInstitutionClick() {
        getInstitutions()
    }

    fun onNotificationsClick() {
        viewModelScope.launch(Dispatchers.IO) {
            errorService.showError("В разработке")
        }
    }

    private fun getInstitutions() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.institutions == null) {
                        _state.update { it.copy(institutionLoadingState = LoadingState.Loading) }
                        val institutions = repository.getInstitutions()
                        _state.update {
                            it.copy(
                                institutions = institutions,
                                institutionLoadingState = LoadingState.Success,
                            )
                        }
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(institutionLoadingState = LoadingState.Error(throwable.toString())) }
                },
            )
        }
    }
}
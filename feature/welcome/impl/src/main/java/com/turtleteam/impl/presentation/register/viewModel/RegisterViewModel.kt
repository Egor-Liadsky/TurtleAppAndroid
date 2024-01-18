package com.turtleteam.impl.presentation.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.repository.WelcomeRepository
import com.turtleteam.api.error.exceptionHandleable
import com.turtleteam.api.models.Institution
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.navigation.WelcomeNavigator
import com.turtleteam.impl.presentation.register.state.RegisterState
import com.turtleteam.storage.InstitutionDataStore
import com.turtleteam.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel(
    private val navigator: WelcomeNavigator,
    private val errorService: ErrorService,
    private val storage: Storage,
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val welcomeRepository: WelcomeRepository by inject()
    private val institutionPreferences: InstitutionDataStore by inject()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    selectInstitution = institutionPreferences.getInstitution(),
                    selectGroup = storage.getGroup(),
                    selectThemeIsDark = storage.getTheme(),
                )
            }
        }
    }

    fun onNextClick() {
        if (_state.value.stage < 3) {
            _state.update { it.copy(stage = _state.value.stage + 1) }
        } else {
            viewModelScope.launch {
                navigator.navigateToGroup()
            }
        }
    }

    fun onBackAction() {
        if (_state.value.stage > 1) {
            _state.update { it.copy(stage = _state.value.stage - 1) }
        } else {
            navigator.onBackButtonClick()
        }
    }

    fun onInstitutionClick() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.institutions == null) {
                        _state.update { it.copy(institutionLoadingState = LoadingState.Loading) }
                        val institutions = welcomeRepository.getInstitutions()
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
                    errorService.showError(throwable.toString())
                },
            )
        }
    }

    fun onGroupClick() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.groups == null) {
                        _state.update { it.copy(groupsLoadingState = LoadingState.Loading) }
                        val groups = welcomeRepository.getGroups().group
                        _state.update {
                            it.copy(
                                groups = groups,
                                groupsLoadingState = LoadingState.Success,
                            )
                        }
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(groupsLoadingState = LoadingState.Error(throwable.toString())) }
                    errorService.showError(throwable.toString())
                },
            )
        }
    }

    fun onSelectInstitutionClick(institution: Institution) {
        viewModelScope.launch(Dispatchers.IO) {
            institutionPreferences.saveInstitution(institution)
            _state.update { it.copy(selectInstitution = institution) }
        }
    }

    fun onSelectGroupClick(group: String) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.saveGroup(group)
            _state.update { it.copy(selectGroup = group) }
        }
    }

    fun onSelectThemeClick(isDark: Boolean) {
        viewModelScope.launch {
            storage.setTheme(isDark)
            _state.update { it.copy(selectThemeIsDark = isDark) }
        }
    }

    fun onTextFieldValueChanged(value: String) {
        _state.update { it.copy(textFieldValue = value) }
    }
}

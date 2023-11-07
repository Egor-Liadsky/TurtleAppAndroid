package com.turtleteam.impl.presentation.screen.register.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.model.Institution
import com.turtleteam.api.data.repository.WelcomeRepository
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.presentation.navigation.WelcomeNavigator
import com.turtleteam.impl.presentation.screen.register.state.RegisterState
import com.turtleteam.storage.Storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val navigator: WelcomeNavigator,
    private val welcomeRepository: WelcomeRepository,
    private val errorService: ErrorService,
    private val storage: Storage,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(selectThemeIsDark = storage.getTheme()) }
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
        viewModelScope.launch {
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

    fun onSelectInstitutionClick(institution: Institution) {
        _state.update { it.copy(selectInstitution = institution) }
    }

    fun onSelectThemeClick(isDark: Boolean) {
        viewModelScope.launch {
            storage.setTheme(isDark)
            _state.update { it.copy(selectThemeIsDark = isDark) }
        }
    }
}

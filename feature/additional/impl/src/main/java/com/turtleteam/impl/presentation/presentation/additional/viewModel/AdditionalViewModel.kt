package com.turtleteam.impl.presentation.presentation.additional.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.repository.AdditionalRepository
import com.turtleteam.core_data.error.exceptionHandleable
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.presentation.presentation.additional.state.AdditionalState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdditionalViewModel : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(AdditionalState())
    val state = _state.asStateFlow()

    private val additionalRepository: AdditionalRepository by inject()
    private val errorService: ErrorService by inject()

    fun onRingsClick() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    _state.update {
                        it.copy(
                            ringsOpened = !it.ringsOpened,
                            ringsLoadingState = LoadingState.Loading
                        )
                    }
                    val rings = additionalRepository.getRings()
                    _state.update {
                        it.copy(
                            rings = rings,
                            ringsLoadingState = LoadingState.Success
                        )
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(ringsLoadingState = LoadingState.Error(throwable.toString())) }
                    errorService.showError(throwable.toString())
                }
            )
        }
    }
}
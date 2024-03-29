package com.turtleteam.impl.presentation.presentation.additional.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyadsky.analytics.AnalyticsService
import com.lyadsky.analytics.models.LogEvent
import com.lyadsky.analytics.models.LogEventParam
import com.turtleteam.api.config.ConfigService
import com.turtleteam.api.data.repository.AdditionalRepository
import com.turtleteam.api.network.error.exceptionHandleable
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

class AdditionalViewModel(
    private val configService: ConfigService,
    private val additionalRepository: AdditionalRepository,
    private val errorService: ErrorService,
    private val analyticsService: AnalyticsService
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(AdditionalState())
    val state = _state.asStateFlow()

    init {
        analyticsService.sendEvent(
            event = LogEvent.OPEN_SCREEN,
            params = mapOf(
                LogEventParam.SCREEN_NAME to "Additional screen",
            )
        )
    }

    fun onRingsClick() {
        getRings()
    }

    fun getPlanshetkaUrl() {
        val planshetkaUrl = configService.getPlanshetkaUrl()
        _state.update { it.copy(planshetkaUrl = planshetkaUrl) }
    }

    fun onChangesClick() {
        viewModelScope.launch(Dispatchers.IO) {
            errorService.showError("В разработке")
        }
    }

    private fun getRings() {
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
                    _state.update {
                        it.copy(
                            ringsOpened = !it.ringsOpened,
                            ringsLoadingState = LoadingState.Error(throwable.toString())
                        )
                    }
                    errorService.showError("Проверьте соединение с интернетом")
                }
            )
        }
    }
}
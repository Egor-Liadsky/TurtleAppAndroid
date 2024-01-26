package com.turtleteam.impl.presentation.group.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.repository.GroupRepository
import com.turtleteam.api.network.error.exceptionHandleable
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.navigation.GroupNavigator
import com.turtleteam.impl.presentation.group.state.GroupState
import com.turtleteam.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GroupViewModel(private val navigator: GroupNavigator) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(GroupState())
    val state = _state.asStateFlow()

    private val storage: Storage by inject()
    private val groupRepository: GroupRepository by inject()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(selectedGroup = storage.getGroup()) }
            getSchedule(state.value.selectedGroup)
        }
    }

    fun onGroupClick() {
        viewModelScope.launch(Dispatchers.IO) {
            getGroups()
        }
    }

    fun onSelectGroupClick(group: String) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.saveGroup(group)
            _state.update { it.copy(selectedGroup = group) }
            getSchedule(group)
        }
    }

    fun onTextFieldValueChanged(value: String) {
        _state.update { it.copy(textFieldValue = value) }
    }

    fun onRefreshSchedule() {
        getSchedule(state.value.selectedGroup)
    }

    fun onRefreshGroups() {
        getGroups()
    }

    private fun getGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.groups == null) {
                        _state.update { it.copy(groupsLoadingState = LoadingState.Loading) }
                        val groups = groupRepository.getGroups().group
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
                },
            )
        }
    }

    private fun getSchedule(group: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (group != null) {
                exceptionHandleable(
                    executionBlock = {
                        _state.update { it.copy(scheduleLoading = LoadingState.Loading) }
                        val schedule = groupRepository.getSchedule(group)
                        _state.update {
                            it.copy(
                                schedule = schedule,
                                scheduleLoading = LoadingState.Success,
                            )
                        }
                    },
                    failureBlock = { throwable ->
                        _state.update { it.copy(scheduleLoading = LoadingState.Error(throwable.toString())) }
                    },
                )
            } else {
                _state.update { it.copy(scheduleLoading = LoadingState.Empty) }
            }
        }
    }
}

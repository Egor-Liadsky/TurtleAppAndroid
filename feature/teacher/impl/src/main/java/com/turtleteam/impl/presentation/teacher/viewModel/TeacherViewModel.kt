package com.turtleteam.impl.presentation.teacher.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.data.repository.TeacherRepository
import com.turtleteam.api.error.exceptionHandleable
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.impl.presentation.teacher.state.TeacherState
import com.turtleteam.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TeacherViewModel : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(TeacherState())
    val state = _state.asStateFlow()

    private val storage: Storage by inject()
    private val teacherRepository: TeacherRepository by inject()
    private val errorService: ErrorService by inject()

    init {
        viewModelScope.launch(Dispatchers.IO) { //TODO попробовать запустить на Main потоке
            _state.update { it.copy(selectedTeacher = storage.getTeacher()) }
            getSchedule(storage.getTeacher())
        }
    }

    fun onTeacherClick() {
        getTeachers()
    }

    fun onSelectTeacherClick(teacher: String) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.saveTeacher(teacher)
            _state.update { it.copy(selectedTeacher = teacher) }
            getSchedule(teacher)
        }
    }

    fun onTextFieldValueChanged(value: String) {
        _state.update { it.copy(textFieldValue = value) }
    }

    fun onRefreshSchedule() {
        getSchedule(state.value.selectedTeacher)
    }

    fun onRefreshTeachers() {
        getTeachers()
    }

    private fun getTeachers() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.teachers == null) {
                        _state.update { it.copy(teachersLoadingState = LoadingState.Loading) }
                        val teachers = teacherRepository.getTeachers().teacher
                        _state.update {
                            it.copy(
                                teachers = teachers,
                                teachersLoadingState = LoadingState.Success,
                            )
                        }
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(teachersLoadingState = LoadingState.Error(throwable.toString())) }
                },
            )
        }
    }

    private fun getSchedule(teacher: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (teacher != null) {
                exceptionHandleable(
                    executionBlock = {
                        _state.update { it.copy(scheduleLoading = LoadingState.Loading) }
                        val schedule = teacherRepository.getSchedule(teacher)
                        _state.update {
                            it.copy(
                                schedule = schedule,
                                scheduleLoading = LoadingState.Success,
                            )
                        }
                    },
                    failureBlock = { throwable ->
                        _state.update { it.copy(scheduleLoading = LoadingState.Error(throwable.toString())) }
                        errorService.showError(throwable.toString())
                    },
                )
            } else {
                _state.update { it.copy(scheduleLoading = LoadingState.Empty) }
            }
        }
    }
}

package com.turtleteam.impl.presentation.register.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.api.models.Institution
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.sheet.SheetWrapper
import com.turtleteam.core_view.view.topbar.StageBar
import com.turtleteam.impl.presentation.register.screen.sheets.InstitutionSheet
import com.turtleteam.impl.presentation.register.screen.layout.SelectGroupLayout
import com.turtleteam.impl.presentation.register.screen.layout.SelectInstitutionLayout
import com.turtleteam.impl.presentation.register.screen.layout.SelectThemeLayout
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(viewModel: RegisterViewModel) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val errorService: ErrorService = koinInject()

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetBackgroundColor = TurtleTheme.color.sheetBackground,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {

            when (state.value.stage) {
                1 -> SheetWrapper(title = "Ваше учебное заведение") {
                    InstitutionSheet(
                        sheetState = sheetState,
                        loadingState = state.value.institutionLoadingState,
                        institutions = state.value.institutions ?: listOf(),
                        selectedInstitution = state.value.selectedInstitution ?: Institution(),
                        onRefresh = { viewModel.onRefreshInstitutions() }
                    ) {
                        viewModel.onSelectInstitutionClick(it)
                    }
                }

                2 -> SheetWrapper(background = Color(0xFFfcfdd3)) {
                    GroupSheet(
                        sheetState = sheetState,
                        textFieldValue = state.value.textFieldValue,
                        onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                        loadingState = state.value.groupsLoadingState,
                        groups = state.value.groups ?: listOf(),
                        selectedGroup = state.value.selectedGroup ?: "",
                        onRefresh = { viewModel.onRefreshGroups() },
                        onClearValueClick = { viewModel.onTextFieldValueChanged("") }
                    ) {
                        viewModel.onSelectGroupClick(it)
                    }
                }
            }
        },
    ) {
        Column(Modifier.padding(top = 20.dp)) {
            StageBar(number = state.value.stage, count = 3)
        }
        Box {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (state.value.stage) {
                    1 -> SelectInstitutionLayout(viewModel = viewModel, sheetState)
                    2 -> SelectGroupLayout(viewModel = viewModel, sheetState)
                    3 -> SelectThemeLayout(viewModel = viewModel)
                }
            }
            CommonButton(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 46.dp, start = 32.dp, end = 32.dp),
                title = "ДАЛЕЕ",
                textColor = TurtleTheme.color.commonButtonTextColor,
                background = TurtleTheme.color.commonButtonBackground,
            ) {
                when (state.value.stage) {
                    1 -> {
                        if (state.value.selectedInstitution?.port != null) {
                            viewModel.onNextClick()
                        } else {
                            scope.launch { errorService.showError("Выберите ваше учебное заведение") }
                        }
                    }

                    2 -> {
                        if (state.value.selectedGroup != null) {
                            viewModel.onNextClick()
                        } else {
                            scope.launch { errorService.showError("Выберите группу") }
                        }
                    }

                    3 -> {
                        viewModel.onNextClick()
                    }
                }
            }
        }
    }
}

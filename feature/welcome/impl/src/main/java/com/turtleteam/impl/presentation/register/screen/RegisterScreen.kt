package com.turtleteam.impl.presentation.register.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.sheet.GroupSheet
import com.turtleteam.core_view.view.topbar.StageBar
import com.turtleteam.impl.presentation.presentation.register.screen.component.InstitutionSheet
import com.turtleteam.impl.presentation.register.screen.layout.SelectGroupLayout
import com.turtleteam.impl.presentation.register.screen.layout.SelectInstitutionLayout
import com.turtleteam.impl.presentation.register.screen.layout.SelectThemeLayout
import com.turtleteam.impl.presentation.presentation.register.viewModel.RegisterViewModel
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Divider(
                    Modifier
                        .width(22.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = TurtleTheme.color.divider,
                )
                Column(Modifier.padding(top = 16.dp)) {
                    when (state.value.stage) {
                        1 -> InstitutionSheet(
                            sheetState = sheetState,
                            registerViewModel = viewModel,
                        )

                        2 -> {
                            GroupSheet(
                                sheetState = sheetState,
                                textFieldValue = state.value.textFieldValue,
                                onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) },
                                loadingState = state.value.groupsLoadingState,
                                groups = state.value.groups ?: listOf(),
                                selectedGroup = state.value.selectGroup ?: "",
                            ) {
                                viewModel.onSelectGroupClick(it)
                            }
                        }
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
                indicationColor = TurtleTheme.color.commonButtonTextColor,
            ) {
                when (state.value.stage) {
                    1 -> {
                        if (state.value.selectInstitution?.port != null) {
                            viewModel.onNextClick()
                        } else {
                            scope.launch { errorService.showError("Выберите ваше учебное заведение") }
                        }
                    }

                    2 -> {
                        if (state.value.selectGroup != null) {
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

package com.turtleteam.impl.presentation.screen.register.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.topbar.StageBar
import com.turtleteam.impl.presentation.screen.register.screen.component.InstitutionSheet
import com.turtleteam.impl.presentation.screen.register.screen.layout.SelectGroupLayout
import com.turtleteam.impl.presentation.screen.register.screen.layout.SelectInstitutionLayout
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WelcomeScreen(viewModel: RegisterViewModel) {

    val state = viewModel.state.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false
    )

    Column(Modifier.fillMaxSize()) {

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetBackgroundColor = TurtleTheme.color.sheetBackground,
            sheetShape = TurtleTheme.shapes.medium,
            sheetContent = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        Modifier
                            .width(22.dp)
                            .height(3.dp)
                            .clip(RoundedCornerShape(3.dp)), color = TurtleTheme.color.divider)
                    Column(Modifier.padding(top = 16.dp)) {
                        when (state.value.stage) {
                            1 -> InstitutionSheet(
                                sheetState = sheetState,
                                institutions = state.value.institutions ?: listOf(),
                                registerViewModel = viewModel
                            )
                        }
                    }
                }
            }
        ) {
            Column(Modifier.padding(top = 20.dp)) {
                StageBar(number = state.value.stage, count = 2)
            }
            when (state.value.stage) {
                1 -> SelectInstitutionLayout(viewModel = viewModel, sheetState)
                2 -> SelectGroupLayout(viewModel = viewModel)
            }
        }
    }
}

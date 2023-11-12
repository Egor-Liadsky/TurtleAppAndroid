package com.turtleteam.impl.presentation.screen.register.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.utils.searchItem
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.sheet.SheetItem
import com.turtleteam.core_view.view.textField.CommonTextField
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupSheet(sheetState: ModalBottomSheetState, registerViewModel: RegisterViewModel) {
    val state = registerViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp),
    ) {
        CommonTextField(
            Modifier.padding(bottom = 16.dp),
            placeholder = "Поиск",
            trailingIcon = R.drawable.ic_search,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() },
            ),
            value = state.value.textFieldValue,
            onValueChange = { registerViewModel.onTextFieldValueChanged(it) },
        )

        when (state.value.groupsLoadingState) {
            LoadingState.Loading -> {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator(
                        Modifier.size(24.dp),
                        color = TurtleTheme.color.textColor,
                    )
                }
            }

            LoadingState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(
                        items = state.value.groups?.searchItem(state.value.textFieldValue)
                            ?: listOf(),
                    ) { group ->
                        if (group != null) {
                            SheetItem(
                                modifier = Modifier.padding(bottom = 5.dp),
                                title = group,
                                isSelected = state.value.selectGroup == group,
                            ) {
                                registerViewModel.onSelectGroupClick(group)
                                scope.launch { sheetState.hide() }
                                focusManager.clearFocus()
                            }
                        }
                    }
                    item {
                        Spacer(Modifier)
                    }
                    item {
                        Spacer(modifier = Modifier.padding(bottom = 28.dp))
                    }
                }
            }

            is LoadingState.Error -> ErrorLayout()
            else -> {}
        }
    }
}

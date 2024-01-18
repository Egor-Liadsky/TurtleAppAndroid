package com.turtleteam.core_view.view.sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.utils.searchItem
import com.turtleteam.core_view.view.layout.EmptyLayout
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.layout.LoadingLayout
import com.turtleteam.core_view.view.textField.CommonTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupSheet(
    sheetState: ModalBottomSheetState,
    textFieldValue: String,
    onTextFieldValueChanged: (String) -> Unit,
    loadingState: LoadingState,
    groups: List<String>,
    selectedGroup: String,
    onRefresh: () -> Unit,
    onSelectGroupClick: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
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
            value = textFieldValue,
            onValueChange = { onTextFieldValueChanged(it) },
        )

        when (loadingState) {
            LoadingState.Loading -> LoadingLayout()

            LoadingState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(148.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(items = groups.searchItem(textFieldValue)) { group ->
                        SheetItem(
                            modifier = Modifier.padding(bottom = 5.dp),
                            title = group,
                            isSelected = selectedGroup == group,
                        ) {
                            onSelectGroupClick(group)
                            scope.launch { sheetState.hide() }
                            focusManager.clearFocus()
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

            LoadingState.Empty -> EmptyLayout(
                image = R.drawable.ic_not_found,
                title = "Пусто"
            )

            is LoadingState.Error -> ErrorLayout {
                onRefresh()
            }
        }
    }
}

package com.turtleteam.impl.presentation.screen.register.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupSheet(sheetState: ModalBottomSheetState, registerViewModel: RegisterViewModel) {
    val state = registerViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp),
    ) {
        when (state.value.groupsLoadingState) {
            LoadingState.Loading -> {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                    items(items = state.value.groups ?: listOf()) { group ->
                        SheetItem(
                            modifier = Modifier.padding(bottom = 5.dp),
                            title = group,
                            isSelected = state.value.selectGroup == group,
                        ) {
                            registerViewModel.onSelectGroupClick(group)
                            scope.launch { sheetState.hide() }
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

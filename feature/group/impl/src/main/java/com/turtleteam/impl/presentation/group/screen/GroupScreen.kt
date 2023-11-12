package com.turtleteam.impl.presentation.group.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.impl.presentation.group.screen.components.GroupSheet
import com.turtleteam.impl.presentation.group.screen.components.GroupTopBar
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupScreen(modifier: Modifier = Modifier, viewModel: GroupViewModel) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

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
                    GroupSheet(sheetState = sheetState, groupViewModel = viewModel)
                }
            }
        },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .then(modifier),
        ) {
            GroupTopBar(
                selectedGroup = state.value.selectedGroup ?: "",
            ) {
                viewModel.onGroupClick()
                scope.launch { sheetState.show() }
            }
            LazyColumn {
                item {
                    Text(state.value.schedule.toString())
                }
            }
        }
    }
}

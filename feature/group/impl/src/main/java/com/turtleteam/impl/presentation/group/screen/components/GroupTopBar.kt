package com.turtleteam.impl.presentation.group.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GroupTopBar(
    selectedGroup: String,
    onGroupClick: () -> Unit,
) {
    Column(
        Modifier
            .background(
                Color(0xFF488166),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            )
            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)),
    ) {
        GroupButton(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
            title = selectedGroup,
        ) {
            onGroupClick()
        }
    }
}

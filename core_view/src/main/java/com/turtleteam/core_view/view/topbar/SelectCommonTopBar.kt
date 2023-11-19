package com.turtleteam.core_view.view.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.view.button.SelectGroupButton

@Composable
fun SelectGroupTopBar(
    selectedGroup: String,
    onGroupClick: () -> Unit,
) {
    Column(
        Modifier
            .background(
                Brush.horizontalGradient(colors = listOf(Color(0xFF488166), Color(0xFFA1C97A))),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            ),
    ) {
        SelectGroupButton(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
            title = selectedGroup,
        ) {
            onGroupClick()
        }
    }
}

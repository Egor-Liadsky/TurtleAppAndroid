package com.turtleteam.core_view.view.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme

@Composable
fun SheetWrapper(
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFfcfdd3))
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
            content()
        }
    }
}
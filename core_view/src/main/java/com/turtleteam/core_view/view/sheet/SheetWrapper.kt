package com.turtleteam.core_view.view.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun SheetWrapper(
    background: Color = TurtleTheme.color.sheetBackground,
    title: String? = null,
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(background)
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
        if (title != null) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.textPrimary,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 16.dp)
                    .clip(
                        RoundedCornerShape(3.dp),
                    ),
                color = Color(0xFFB9B9B9),
            )
        }
        Column(Modifier.padding(top = 20.dp)) {
            content()
        }
    }
}

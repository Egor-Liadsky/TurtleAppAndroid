package com.turtleteam.impl.presentation.group.screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun AdditionalButton(
    modifier: Modifier = Modifier,
    title: String,
    onButtonClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F6F1).copy(0.76f)),
        border = BorderStroke(1.dp, TurtleTheme.color.textColor),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = fontQanelas,
                color = TurtleTheme.color.textColor,
                textAlign = TextAlign.Center,
            ),
        )
    }
}
